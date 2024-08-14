package com.example.ChatApp.services.impl;

import com.example.ChatApp.data.user.request.LoginRequestDto;
import com.example.ChatApp.models.User;
import com.example.ChatApp.repository.UserRepository;
import com.example.ChatApp.services.UserService;
import com.example.ChatApp.utils.validators.UserValidator;
import com.example.ChatApp.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateNewUser(User user) {
        String enteredUsername = user.getUsername();
        UserValidator.checkValidCredentials(user, /* isNewUser */ true);
        Validator.validateValueNotAlreadyPresent(userRepository.findByUsername(enteredUsername), "Username");
    }

    public void validateExistingUser(String username) {
        Validator.validateValuePresent(userRepository.findByUsername(username), "Username");
    }

    @Override
    public void login(LoginRequestDto loginRequestDto) {
        validateExistingUser(loginRequestDto.username());
    }

    @Override
    public User saveUser(User user) {
        validateNewUser(user);
        return userRepository.save(user);
    }

}
