package com.example.ChatApp.services;

import com.example.ChatApp.Models.Entity.User;
import com.example.ChatApp.repository.UserRepository;
import com.example.ChatApp.utils.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private void validateNewUser(User user) {
        String enteredUsername = user.getUsername();
        UserValidator.checkValidCredentials(user, /* isNewUser */ true);
        UserValidator.validateValueNotAlreadyPresent(userRepository.findById(enteredUsername), "Username");
    }

    public User saveUser(User user) {
        validateNewUser(user);
        return userRepository.save(user);
    }

}
