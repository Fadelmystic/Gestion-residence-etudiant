package com.example.prj.controller;

import com.example.prj.entity.Room;
import com.example.prj.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")

@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.createRoom(room));
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms() {
        return ResponseEntity.ok(roomService.getAvailableRooms());
    }
    
    @PostMapping("/{roomId}/assign/{studentId}")
    public ResponseEntity<Room> assignStudent(
            @PathVariable Long roomId,
            @PathVariable Long studentId) {
        return ResponseEntity.ok(roomService.assignStudentToRoom(roomId, studentId));
    }
}