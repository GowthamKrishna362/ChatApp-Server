package com.example.ChatApp.Repository;

import com.example.ChatApp.Models.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
