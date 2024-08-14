package com.example.ChatApp;

import com.example.ChatApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChatAppApplication {

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ChatAppApplication.class, args);

	}


}
