package com.example.prj.service;

import com.example.prj.dto.RoomDTO;
import com.example.prj.entity.Room;
import com.example.prj.entity.RoomStatus;
import com.example.prj.entity.Student;
import com.example.prj.repository.RoomRepository;
import com.example.prj.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service

public class RoomService {
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public RoomService(RoomRepository roomRepository, StudentRepository studentRepository, StudentService studentService) {
        this.roomRepository = roomRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    public Room createRoom(Room room) {
        if (roomRepository.existsByRoomNumber(room.getRoomNumber())) {
            throw new RuntimeException("Room number already exists");
        }
        return roomRepository.save(room);
    }
    
    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailable(true);
    }

    public Room updateRoom(Long id, RoomDTO room) {
        Optional<Room> existingRoomOptional = roomRepository.findById(id);

        if (existingRoomOptional.isPresent()) {
            Room existingRoom = existingRoomOptional.get();
            existingRoom.setRoomNumber(room.getRoomNumber());
            existingRoom.setSize(room.getSize());
            existingRoom.setAvailable(room.getStatus() == RoomStatus.AVAILABLE);
            existingRoom.setStatus(room.getStatus());
            if(room.getStudentId() != null)
            {
                Student student = studentService.getStudent(room.getStudentId());
                existingRoom.setStudent(student);
            } else {
                existingRoom.setStudent(null);
            }
            return roomRepository.save(existingRoom); // Save updated room
        } else {
            throw new RuntimeException("Room with ID " + id + " not found!");
        }
    }

    // Delete a Room by ID
    public void deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id); // Delete the room
        } else {
            throw new RuntimeException("Room with ID " + id + " not found!");
        }
    }
    // Get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll(); // Return a list of all rooms
    }

    // Récupérer une chambre par son numéro
    public Room getRoomByRoomNumber(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new RuntimeException("Room with number " + roomNumber + " not found!"));
    }
    // Récupérer toutes les chambres occupées (non disponibles)
    public List<Room> getOccupiedRooms() {
        return roomRepository.findByAvailable(false); // Filtrer les chambres non disponibles
    }
    // Récupérer une chambre par son ID
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room with ID " + id + " not found!"));
    }

    public Room getRoomByStudentId(Long studentId) {
        return roomRepository.findByStudentId(studentId);
    }


    // Changer le statut d'une chambre
    public Room updateRoomStatus(Long id, RoomStatus status) {
        Room room = getRoomById(id);
        room.setStatus(status); // Modifie le statut
        return roomRepository.save(room); // Sauvegarde la chambre mise à jour
    }
    // Assigner un étudiant à une chambre
    public Room assignStudentToRoom(Long roomId, Long studentId) {
        Room room = getRoomById(roomId);
        room.setAssignedStudentId(studentId);
        room.setAvailable(false);
        return roomRepository.save(room); // Sauvegarder la chambre mise à jour
    }

    // Libérer une chambre
    public Room freeRoom(Long roomId) {
        Room room = getRoomById(roomId);
        room.setAvailable(true); // Met la chambre disponible
        room.setAssignedStudentId(null); // Supprime l'assignation de l'étudiant
        return roomRepository.save(room); // Sauvegarde la chambre mise à jour
    }
    // Récupérer les chambres selon plusieurs critères
    public List<Room> getRoomsByCriteria(Double size, boolean available, String status) {
        return roomRepository.findBySizeAndAvailableAndStatus(size, available, status); // Filtrer selon plusieurs critères
    }


    public Room selectRoom(Long roomId, Long studentId) {
        return roomRepository.SelectRoomS(roomId, studentId);

    }

    public void UpdateRoomP(Long roomId, Long studentId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NoSuchElementException("Room not found"));
        Student student = studentService.getStudent(studentId);
        if (room.getStatus() == RoomStatus.AVAILABLE){
            room.setStatus(RoomStatus.OCCUPIED);
            room.setAvailable(false);
            room.setStudent(student);
            roomRepository.save(room);
        } else {
            throw new IllegalStateException("This room is not available");
        }
    }

    public List<Room> getRoomsByStudentId(Long id) {
        return roomRepository.findByStudent_Id(id);
    }

    public Room createRoomR(RoomDTO registerRequest) {
        Room room = new Room(
                registerRequest.getRoomNumber(),
                registerRequest.getSize(),
                registerRequest.getStatus(),
                null
        );
        if(registerRequest.getStudentId() != null){
            Student student = studentService.getStudent(registerRequest.getStudentId());
            room.setStudent(student);
        }
        room.setAvailable(room.getStatus() == RoomStatus.AVAILABLE);
        return roomRepository.save(room);
    }

    public Room updateRoomR(Long id, Room room) {
        Optional<Room> existingRoomOptional = roomRepository.findById(id);

        if (existingRoomOptional.isPresent()) {
            Room existingRoom = existingRoomOptional.get();
            existingRoom.setRoomNumber(room.getRoomNumber());
            existingRoom.setSize(room.getSize());
            existingRoom.setAvailable(room.isAvailable());
            existingRoom.setStatus(room.getStatus());
            if(room.getStudent() != null) {
                Student student = studentService.getStudent(room.getStudent().getId());
                existingRoom.setStudent(student);
            } else {
                existingRoom.setStudent(null);
            }
            return roomRepository.save(existingRoom); // Save updated room
        } else {
            throw new RuntimeException("Room with ID " + id + " not found!");
        }
    }

}