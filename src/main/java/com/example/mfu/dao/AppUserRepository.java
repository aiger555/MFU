package com.example.mfu.dao;

import com.example.mfu.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    public AppUser findByName(String name);
    public AppUser findByEmail(String email);
}
