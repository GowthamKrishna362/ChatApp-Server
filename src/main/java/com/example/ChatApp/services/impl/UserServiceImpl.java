package com.example.ChatApp.services.impl;

import com.example.ChatApp.data.dto.LoginRequestDto;
import com.example.ChatApp.models.Entity.User;
import com.example.ChatApp.repository.UserRepository;
import com.example.ChatApp.utils.validators.UserValidator;
import com.example.ChatApp.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    private void validateNewUser(User user) {
        String enteredUsername = user.getUsername();
        UserValidator.checkValidCredentials(user, /* isNewUser */ true);
        Validator.validateValueNotAlreadyPresent(userRepository.findByUsername(enteredUsername), "Username");
    }

    private void validateExistingUser(String username) {
        Validator.validateValuePresent(userRepository.findByUsername(username), "Username");
    }

    public void login(LoginRequestDto loginRequestDto) {
        validateExistingUser(loginRequestDto.username());
    }

    public User saveUser(User user) {
        validateNewUser(user);
        return userRepository.save(user);
    }

}
