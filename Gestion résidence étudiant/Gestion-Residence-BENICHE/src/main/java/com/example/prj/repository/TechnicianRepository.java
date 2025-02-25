package com.example.prj.repository;

import com.example.prj.entity.Student;
import com.example.prj.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    @Query("SELECT t FROM Technician t WHERE  t.available = :available")
    List<Technician> findByAvailable(@Param("available") boolean available);


      @Query("SELECT t FROM Technician t WHERE t.id = :id")
      Technician findByUserId(@Param("id") Long id) ;
}