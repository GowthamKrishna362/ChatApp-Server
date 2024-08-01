package com.example.ChatApp.Services;

import com.example.ChatApp.Models.Entity.User;
import com.example.ChatApp.Repository.UserRepository;
import com.example.ChatApp.Utils.StringUtils;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private void checkValidCredentials(User user, boolean isNewUser) {
        if (StringUtils.isNullOrBlank(user.getUsername())) {
            throw new ValidationException("Username is required");
        }
        if (StringUtils.isNullOrBlank(user.getPassword())) {
            throw new ValidationException("Password is required");
        }
        if (isNewUser && StringUtils.isNullOrBlank(user.getName())) {
            throw new ValidationException("Name is required");
        }
    }

    private void validateNewUser(User user) {
        String enteredUsername = user.getUsername();
        checkValidCredentials(user, /*isNewUser*/ true);
        if(userRepository.findById(enteredUsername).isPresent()) {
            throw new ValidationException("Username " + enteredUsername +  " already exists");
        }
    }

    public User saveUser(User user) {
        validateNewUser(user);
        return userRepository.save(user);
    }

}
