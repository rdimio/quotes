package ru.vadimr.quotes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String login;
    private String password;
    private String confirmPassword;
    private String email;
    private String token;
}
