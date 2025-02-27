package com.demo.emailservice.controller;

import com.demo.emailservice.model.User;
import com.demo.emailservice.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class UserController {

    private final EmailService emailService;

    public UserController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) throws MessagingException, UnsupportedEncodingException {
        Map<String, String> response = new HashMap<>();

        emailService.sendMailWithInline(user);
        response.put("message", "User created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
