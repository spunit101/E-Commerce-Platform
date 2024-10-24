package com.spunit.UserManagement.request;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class LoginRequest {

    private String username;
    private String password;
}
