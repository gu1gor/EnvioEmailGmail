package com.demo.emailservice.controller;

import com.demo.emailservice.model.User;
import com.demo.emailservice.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class UserController {

    @Autowired
    private EmailService emailService;


    @PostMapping
    public ResponseEntity<Object> register (@RequestBody User user)
            throws MessagingException, UnsupportedEncodingException {
        emailService.sendMailWithInline(user);

        Map<String, String> body = new HashMap<>();
        body.put("message", "User created successfully");

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
