package com.example.ChatApp.services.impl;

import com.example.ChatApp.data.exception.UserNotFoundException;
import com.example.ChatApp.data.user.SliceOfUsers;
import com.example.ChatApp.data.user.LoginRequestDto;
import com.example.ChatApp.models.User;
import com.example.ChatApp.repository.UserRepository;
import com.example.ChatApp.services.UserService;
import com.example.ChatApp.utils.validators.UserValidator;
import com.example.ChatApp.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void validateNewUser(User user) {
        String enteredUsername = user.getUsername();
        UserValidator.checkValidCredentials(user, /* isNewUser */ true);
        Validator.validateValueNotAlreadyPresent(userRepository.findByUsername(enteredUsername), "Username");
    }


    @Override
    public User login(LoginRequestDto loginRequestDto) throws UserNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.username(),
                loginRequestDto.password()
        ));
        return userRepository.findByUsername(loginRequestDto.username()).orElseThrow(() -> new UserNotFoundException(loginRequestDto.username()));
    }

    @Override
    public User saveUser(User user) {
        validateNewUser(user);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public SliceOfUsers getSliceOfUsersByPrefix(String prefix) {
        Pageable pageable = PageRequest.of(0,10);
        Slice<User> userSlice =userRepository.findByUsernameStartingWith(prefix, pageable);
        return new SliceOfUsers(userSlice);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

    }
}
