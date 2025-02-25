//package com.example.prj.repository;
//
//import com.example.prj.entity.User;
//import jakarta.transaction.Transactional;
//import jakarta.validation.constraints.Email;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.email = :email")
//    User findByEmail(@Param("email") String email);
//
//    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
//    boolean existsByEmail(@Param("email") String email);
//
//    @Query("SELECT u FROM User u WHERE u.username = :username")
//    Optional<User> findByUsername(@Param("username") String username);
//
//    @Query("SELECT u FROM User u WHERE u.id = :id")
//     Optional<User> findByUserId(@Param("id") Long id) ;
//
//    @Query("SELECT u FROM User u WHERE u.email = :email and u.password= :password ")
//    User findByEmailAndPwd(@Param("email") String email, @Param("password") String password);
//
//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO users (first_name, last_name, email, phone_number, created_at, active, username, role_id) " +
//            "VALUES (:firstName, :lastName, :email, :phoneNumber, :createdAt, :active, :username, :roleId)",
//            nativeQuery = true)
//    void saveUser(@Param("firstName") String firstName,
//                  @Param("lastName") String lastName,
//                  @Param("email") String email,
//                  @Param("phoneNumber") String phoneNumber,
//                  @Param("createdAt") LocalDateTime createdAt,
//                  @Param("active") boolean active,
//                  @Param("username") String username,
//                  @Param("roleId") int roleId);
//}
//
