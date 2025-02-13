// Gestion des modals
function showLoginModal() {
    document.getElementById('loginModal').classList.add('active');
}

function hideLoginModal() {
    document.getElementById('loginModal').classList.remove('active');
}

function showRegisterModal() {
    document.getElementById('registerModal').classList.add('active');
}

function hideRegisterModal() {
    document.getElementById('registerModal').classList.remove('active');
}

async function handleLogin(event) {
    event.preventDefault();

    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();

    if (!email || !password) {
        alert('Veuillez remplir tous les champs.');
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password }),
            credentials: 'include' // Important pour que les cookies de session soient envoyés
        });

        if (response.ok) {
            const data = await response.json();
            if (data) {
                sessionStorage.setItem('student', JSON.stringify(data));
                alert('Connexion réussie !');
                window.history.pushState({}, "", '/profile');
                window.location.href = '/profile'; // Redirection directe
            } else {
                alert('Seuls les étudiants peuvent se connecter ici.');
            }
        } else {
            alert('Erreur de connexion. Vérifiez vos informations.');
        }
    } catch (error) {
        console.error(error);
        alert('Une erreur est survenue. Réessayez plus tard.');
    }
}

async function handleRegister(event) {
    event.preventDefault();

    const userData = {
        firstName: document.getElementById('regFirstName').value.trim(),
        lastName: document.getElementById('regLastName').value.trim(),
        username: document.getElementById('regUsername').value.trim(),
        email: document.getElementById('regEmail').value.trim(),
        password: document.getElementById('regPassword').value.trim(),
        dateNaissance: document.getElementById('regDateNaissance').value.trim(),
    };

    if (Object.values(userData).some(field => !field)) {
        alert('Veuillez remplir tous les champs.');
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/api/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userData),
            credentials: 'include'
        });

        if (response.ok) {
            const data = await response.json();
            if (data) {
                sessionStorage.setItem('student', JSON.stringify(data));
                alert('Inscription réussie!');
                window.history.pushState({}, "", '/profile');
                window.location.href = '/profile'; // Redirection directe
            } else {
                alert('Seuls les étudiants peuvent sinscrire ici.');
            }

        } else {
            const error = await response.json();
            alert(`Erreur d'inscription : ${error.message}`);
        }
    } catch (error) {
        console.error(error);
        alert('Une erreur est survenue. Réessayez plus tard.');
    }
}


async function checkSession() {
    try {
        const response = await fetch('http://localhost:8080/api/auth/profile', {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include'
        });
        if(response.ok){
            window.history.pushState({}, "", '/profile');
            window.location.href = '/profile';
        }
    } catch (e){
        console.error("Error while checking session",e)
    }
}
// Vérification automatique du rôle pour afficher les boutons de connexion/déconnexion
function updateAuthButtons() {
    const authButtons = document.getElementById('authButtons');

    fetch('http://localhost:8080/api/auth/check-session', {
        method: 'GET',
        credentials: 'include'
    })
        .then(response => {
            if (response.ok) {
                authButtons.innerHTML = `
                <button onclick="logout()" class="btn btn-logout">Déconnexion</button>
            `;
            } else {
                authButtons.innerHTML = `
                <button onclick="showLoginModal()" class="btn btn-login">Connexion</button>
                <button onclick="showRegisterModal()" class="btn btn-register">Inscription</button>
            `;
            }
        })
        .catch(error => console.error(error));
}

// Déconnexion de l'utilisateur
function logout() {
    fetch('http://localhost:8080/api/auth/logout', {
        method: 'POST',
        credentials: 'include'  // Inclure les cookies pour gérer la session
    })
        .then(() => {
            alert('Déconnexion réussie.');
            updateAuthButtons();
            window.location.href = '/';
        })
        .catch(error => console.error(error));
}
document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    loginForm.addEventListener('submit', handleLogin);
    const registerForm = document.getElementById('registerForm');
    registerForm.addEventListener('submit', handleRegister);
    updateAuthButtons();
    checkSession();
});