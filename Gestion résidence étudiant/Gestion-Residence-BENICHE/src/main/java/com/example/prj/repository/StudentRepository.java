
package com.example.prj.repository;

import com.example.prj.entity.Student;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


    @Repository
    public interface StudentRepository extends JpaRepository<Student, Long> {
        @Query("SELECT u FROM Student u WHERE u.email = :email")
        Student findByEmail(@Param("email") String email);

        @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Student u WHERE u.email = :email")
        boolean existsByEmail(@Param("email") String email);

        @Query("SELECT u FROM Student u WHERE u.username LIKE '%username'\n")
        Optional<Student> findByUsername(@Param("username") String username);

        @Query("SELECT u FROM Student u WHERE u.id = :id")
        Student findByUserId(@Param("id") Long id) ;

        @Query("SELECT u FROM Student u WHERE u.email = :email and u.password= :password ")
        Student findByEmailAndPwd(@Param("email") String email, @Param("password") String password);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Student (first_name, last_name, email,  username, password, dateNaissance) " +
                "VALUES (:firstName, :lastName, :email, :phoneNumber, :createdAt, :active, :username, :roleId)",
                nativeQuery = true)
        void saveUser(@Param("firstName") String firstName,
                      @Param("lastName") String lastName,
                      @Param("email") String email,
                      @Param("dateNaissance") LocalDateTime dateNaissance,
                      @Param("username") String username,
                      @Param("password") String password);

        @Query("SELECT COUNT(s) FROM Student s")
        int countTotalStudents();

        Student findByEmailAndPassword(String email, String password);
    }






