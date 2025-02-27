package com.demo.emailservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String name;
    private String email;

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "'}";
    }
}
