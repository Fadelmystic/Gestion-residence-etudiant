// Store data in localStorage
const store = {
    rooms: [],
    students: [],
    technicians: [],
    maintenance: [],
    payment: []
};
const originalData = {
    rooms: [],
    students: [],
    technicians: [],
    maintenance: [],
    payment: []
};


// Show/Hide sections
function showSection(sectionId) {
    document.querySelectorAll('.section').forEach(section => section.classList.add('hidden'));
    document.getElementById(sectionId).classList.remove('hidden');
    loadData(sectionId);

}

// Handle form submissions
async function handleSubmit(event, type) {
    event.preventDefault();
    const form = event.target;
    const id = form.querySelector('#' + type + '-id').value
    const formData = new FormData(form);
    const data = Object.fromEntries(formData);

    const method = id ? 'PUT' : 'POST';
    const url = id ? `/api/admin/${type}/${id}` : `/api/admin/${type}`
    try {
        const response = await fetch(url, {
            method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });

        if (response.ok) {
            alert(`Element ${method === 'PUT' ? 'modifié' : 'ajouté'} avec succès!`);
            form.reset();
            document.getElementById(`${type}-form`).classList.add('hidden')
            loadData(type)


        } else {
            const errorData = await response.json()
            console.error('Erreur de l\'API:', errorData);
            alert(`Erreur lors de l'opération: ${response.statusText} : ${JSON.stringify(errorData)}`);
        }
    } catch (error) {
        console.error('Erreur lors de l\'appel à l\'API:', error);
        alert('Erreur serveur lors de l\'opération.');
    }
}

// Generic filter function
function filterData(type, data, filters) {
    console.log(`Filtering ${type} data with filters:`, filters);

    if (!filters || Object.keys(filters).length === 0) {
        console.log(`No filters for ${type}, returning original data`);
        return data;
    }


    return data.filter(item => {
        let isMatch = true; // Default to true, only filter out if conditions are not met

        for (const key in filters) {
            const filterValue = filters[key]
            if(!filterValue || !item.hasOwnProperty(key)){
                continue;
            }

            if (type==='rooms') {
                if (key === 'roomNumber'){
                    const itemValue = String(item.roomNumber).toLowerCase();
                    if(!itemValue.includes(filterValue.toLowerCase())){
                        isMatch = false;
                    }
                }  else if (key === 'sizeMin') {
                    const itemSize = Number(item.size);
                    if (isNaN(Number(filterValue)) || itemSize < Number(filterValue)) {
                        isMatch = false
                    }
                } else if (key === 'sizeMax') {
                    const itemSize = Number(item.size);
                    if (isNaN(Number(filterValue)) || itemSize > Number(filterValue)) {
                        isMatch = false
                    }
                }
            }else if(type==='students' || type==='technicians'){
                if (key === 'name') {
                    const itemName = (item.firstName + ' ' + item.lastName).toLowerCase();
                    if (!itemName.includes(filterValue.toLowerCase())) {
                        isMatch = false;
                    }
                }
            }
            else if (type === 'maintenance') {
                if (key === 'status' ) {
                    const itemValue =  String(item.status).toLowerCase()
                    if(!itemValue.includes(filterValue.toLowerCase()))
                        isMatch = false;
                } else if (key === 'priority' ) {
                    const itemValue =  String(item.priority).toLowerCase()
                    if(!itemValue.includes(filterValue.toLowerCase()))
                        isMatch = false;
                }
            } else if (type === 'payment') {
                if(key==='amountMin'){
                    const itemValue = Number(item.amount);
                    if(isNaN(Number(filterValue)) || itemValue<Number(filterValue))
                        isMatch=false;
                }
                else if(key==='amountMax'){
                    const itemValue = Number(item.amount);
                    if(isNaN(Number(filterValue)) || itemValue>Number(filterValue))
                        isMatch=false
                }
            }

        }


        if(!isMatch) {
            console.log(`Item failed filters:`,item);
            return false
        }
        console.log(`Item passed all filters:`, item);
        return true
    });
    console.log(`Filtered data for ${type}:`, filteredData);
    return filteredData;
}


// Render list
function renderList(type, data) {
    const list = document.getElementById(`${type}-list`);
    if(data.length==0){
        list.innerHTML = `<div style="text-align:center;">Aucun element a afficher</div>`;
        return;
    }
    if (type === 'rooms') {
        list.innerHTML = data
            .map(room => `
                    <div class="list-item">
                        <div class="item-content">${room.roomNumber} - ${room.status} - ${room.size}m² -${room.student_id}- ${room.available ? 'Disponible' : 'Non disponible' }</div>
                        <div class="action-buttons">
                            <button class="edit-btn" onclick="editRoom('${room.id}')">Modifier</button>
                            <button onclick="deleteItem('${type}', '${room.id}')">Supprimer</button>
                        </div>
                    </div>
                `)
            .join('');
    } else if (type === 'students') {
        list.innerHTML = data
            .map(student=> `
                <div class="list-item">
                    <div class="item-content">
                        <!-- Assuming data contains the student and user fields -->
                        <p><strong>ID:</strong> ${student.id} </p>
                        <p><strong>First Name:</strong> ${student.firstName}</p>
                        <p><strong>Last Name:</strong> ${student.lastName}</p>
                        <p><strong>Username:</strong> ${student.username}</p>
                        <p><strong>Email:</strong> ${student.email}</p>
                        <p><strong>Password :</strong> ${student.password}</p>
                        <p><strong>Date Naissance:</strong> ${student.dateNaissance}</p>
                    </div>
                    <div class="action-buttons">
                        <button class="edit-btn" onclick="editItem('${type}', '${student.id}')">Modifier</button>
                        <button onclick="deleteItem('${type}', '${student.id}')">Supprimer</button>
                    </div>
                </div>
            `)
            .join('');
    } else if (type === 'technicians') {
        list.innerHTML = data
            .map(item => `
                <div class="list-item">
                 <div class="item-content">
                    <p><strong>ID:</strong> ${item.id} </p>
                        <p><strong>First Name:</strong> ${item.firstName}</p> </br>
                        <p><strong>Last  Name:</strong> ${item.lastName}</p> </br>
                        <p><strong>Username:</strong> ${item.username}</p> </br>
                        <p><strong>Email:</strong> ${item.email}</p>    </br>           
                        <p><strong>Password :</strong> ${item.password}</p> </br>
                        <p><strong>Available :</strong> ${item.available}</p> </br>
                        </div>
                    <div class="action-buttons">
                        <button class="edit-btn" onclick="editItem('${type}', '${item.id}')">Modifier</button>
                        <button onclick="deleteItem('${type}', '${item.id}')">Supprimer</button>
                    </div>
                </div>
            `)
            .join('');
    } else if (type === 'maintenance') {
        list.innerHTML = data
            .map(item => `
                <div class="list-item">
                 <div class="item-content">
                    <p><strong>ID:</strong> ${item.id} </p>
                        <p><strong>Priority:</strong> ${item.priority}</p> 
                        <p><strong>Status:</strong> ${item.status}</p> 
                        <p><strong>Description:</strong> ${item.description}</p> 
                        <p><strong>Reported Date:</strong> ${item.reportedDate}</p>         
                        <p><strong>Resolved Date :</strong> ${item.resolvedDate}</p> 
                        </div>
                    <div class="action-buttons">
                        <button class="edit-btn" onclick="editItem('${type}', '${item.id}')">Modifier</button>
                        <button onclick="deleteItem('${type}', '${item.id}')">Supprimer</button>
                    </div>
                </div>
            `)
            .join('');
    } else if (type === 'payment') {
        list.innerHTML = data
            .map(item => `
                <div class="list-item">
                 <div class="item-content">
                    <p><strong>ID:</strong> ${item.id} </p>
                        <p><strong>Status:</strong> ${item.status}</p> 
                        <p><strong>Amount:</strong> ${item.amount}</p> 
                        <p><strong>Payment Method:</strong> ${item.paymentMethod}</p>         
                 </div>
                    <div class="action-buttons">
                        <button class="edit-btn" onclick="editItem('${type}', '${item.id}')">Modifier</button>
                        <button onclick="deleteItem('${type}', '${item.id}')">Supprimer</button>
                    </div>
                </div>
            `)
            .join('');
    }

}

// Delete item
async function deleteItem(type, id) {
    const url = type === 'payment' ? `/api/admin/payments/${id}` : `/api/admin/${type}/${id}`;
    fetch(url, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                loadData(type);  // Reload the list
            } else {
                alert("Erreur lors de la suppression.");
            }
        })
        .catch(error => {
            console.error("Erreur lors de la suppression:", error);
        });
}

// Edit item
function editRoom(id) {
    window.location.href = `/admin/edit-room?id=${id}`;
}
async function editItem(type, id) {
    if(type=="students"){
        window.location.href = `/admin/edit-student?type=${type}&id=${id}`
    }
    if(type=="technicians"){
        window.location.href = `/admin/edit-technician?type=${type}&id=${id}`
    }
    if(type=="maintenance"){
        window.location.href = `/admin/edit-maintenance?type=${type}&id=${id}`
    }
    if(type=="payment"){
        window.location.href = `/admin/edit-payment?type=${type}&id=${id}`
    }
}
// Update statistics
async function updateStatistics() {
    try {
        const [roomsResponse, studentsResponse, maintenanceResponse, paymentsResponse] = await Promise.all([
            fetch("/api/admin/rooms"),
            fetch("/api/admin/students"),
            fetch("/api/admin/maintenance"),
            fetch("/api/admin/payments")
        ]);

        if (roomsResponse.ok && studentsResponse.ok && maintenanceResponse.ok && paymentsResponse.ok) {
            const rooms = await roomsResponse.json();
            const students = await studentsResponse.json();
            const maintenance = await maintenanceResponse.json();
            const payments = await paymentsResponse.json();

            document.getElementById('occupied-rooms').textContent = rooms.filter(r => r.status === 'OCCUPIED').length;
            document.getElementById('active-students').textContent = students.length;
            document.getElementById('pending-maintenance').textContent = maintenance.filter(m => m.status === 'pending').length;
            document.getElementById('monthly-payments').textContent = payments.reduce((sum, p) => sum + parseFloat(p.amount || 0), 0).toFixed(2) + '€';
        } else {
            console.error('Erreur lors de la récupération des données pour les statistiques')
        }
    } catch (error) {
        console.error("Erreur lors de la récupération des statistiques:", error);
    }
}

async function loadData(section) {
    try {
        let url = `/api/admin/${section}`;
        if (section==='payment') {
            url =`/api/admin/payments`
        }
        const response = await fetch(url);
        if (response.ok) {
            const data = await response.json();
            originalData[section] = [...data]; // Store copy
            renderList(section, data);
            updateStatistics();
        } else {
            console.error(`Erreur lors du chargement des ${section}`, response);
        }
    } catch (error) {
        console.error('erreur lors du chargement', error);
    }

}

document.addEventListener("DOMContentLoaded", () => {
    loadData('rooms');
});
function showForm(section) {
}
const searchInputs = {
    rooms: ['room-number-search', 'room-size-min-search','room-size-max-search'],
    students: ['student-name-search'],
    technicians: ['technician-name-search'],
    maintenance: ['maintenance-status-search','maintenance-priority-search'],
    payment: ['payment-date-search', 'payment-amount-min-search', 'payment-amount-max-search'],
};
for (const section in searchInputs) {
    searchInputs[section].forEach(input => {
        const searchInput = document.getElementById(input);
        searchInput.addEventListener('input', (event) => {
            const filters = {};
            searchInputs[section].forEach(inputId => {
                const searchInput = document.getElementById(inputId);
                if (searchInput.value) {
                    filters[inputId.replace(`-${inputId.split('-').pop()}`, '')] = searchInput.value
                }
            });
            console.log(`Filtering ${section} with inputs: `, filters)
            const filteredData = filterData(section, originalData[section], filters);
            console.log(`Filtered Data for ${section}: `, filteredData)
            renderList(section,filteredData)
        });
    });
}