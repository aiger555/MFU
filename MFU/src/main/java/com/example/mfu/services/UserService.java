package com.example.mfu.services;

import com.example.mfu.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {// for security
    boolean save(UserDTO userDto);
}
