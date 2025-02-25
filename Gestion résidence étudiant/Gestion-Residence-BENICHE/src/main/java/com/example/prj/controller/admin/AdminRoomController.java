package com.example.prj.controller.admin;

import com.example.prj.classes.RoomReservation;
import com.example.prj.dto.RoomDTO;
import com.example.prj.entity.Room;
import com.example.prj.entity.RoomStatus;
import com.example.prj.entity.Student;
import com.example.prj.repository.RoomRepository;
import com.example.prj.service.RoomService;
import com.example.prj.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/rooms")

@CrossOrigin(origins = "http://localhost:3000")
public class AdminRoomController {
    private final RoomService roomService;
    private final RoomRepository roomRepository;
    private final StudentService studentService;

    public AdminRoomController(RoomService roomService, RoomRepository roomRepository, StudentService studentService) {
        this.roomService = roomService;
        this.roomRepository = roomRepository;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable  Long id) {
        return ResponseEntity.ok( roomRepository.getRoom(id));
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.createRoomR(roomDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody RoomDTO room ) {
        return ResponseEntity.ok(roomService.updateRoom(id, room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok().build();
    }
}