package com.example.ChatApp.Models.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "USERS")
public class User {
    @Id
    private String username;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    public User(String username) {
        this.username = username;
    }

}
