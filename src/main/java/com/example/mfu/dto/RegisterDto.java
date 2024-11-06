package com.example.mfu.dto;

import com.example.mfu.entities.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterDto {
    @NotEmpty
    private String firstname;

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 6, message = "Min password length is 6 characters")
    private String password;

    private Role role;

}
