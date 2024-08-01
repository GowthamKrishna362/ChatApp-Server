package com.example.ChatApp.Repository;

import com.example.ChatApp.Models.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.id IN :ids")
    List<User> findByUsernames(@Param("ids") List<String> usernames);
}
