package com.example.prj.repository;

import com.example.prj.entity.Payment;
import com.example.prj.entity.Room;
import com.example.prj.entity.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE r.available = :available")
    List<Room> findByAvailable(@Param("available") boolean available);

    @Query("SELECT COUNT(r) > 0 FROM Room r WHERE r.roomNumber = :roomNumber")
    boolean existsByRoomNumber(@Param("roomNumber") String roomNumber);


    @Query("SELECT COUNT(r) FROM Room r")
    int countTotalRooms();

    // Compter le nombre de chambres occupées
    // Compter le nombre de chambres occupées
    @Query("SELECT COUNT(r) FROM Room r WHERE r.available = false")
    int countOccupiedRooms();


    @Query("SELECT r FROM Room r WHERE r.roomNumber = :roomNumber")
    Optional<Room> findByRoomNumber(@Param("roomNumber") String roomNumber);


    @Query("SELECT r FROM Room r WHERE CAST(r.size AS double ) = :size AND r.available = :available AND r.status = :status")
    List<Room> findBySizeAndAvailableAndStatus(@Param("size") Double size, @Param("available") boolean available, @Param("status") String status);

    @Query("SELECT r FROM Room r WHERE r.id = :id")
    Room getRoom(@Param("id") Long id);

    @Query("SELECT  r FROM Room r WHERE r.student.id = :studentId")
    Room findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT  r FROM Room r WHERE r.student.id = :studentId and r.id=:roomId")
    Room SelectRoomS(@Param("roomId") Long RoomId,@Param("studentId") Long studentId);

    @Query("SELECT r FROM Room r WHERE r.student.id = :studentId ")
    List<Room> findByStudent_Id(@Param("studentId") Long studentId);
}