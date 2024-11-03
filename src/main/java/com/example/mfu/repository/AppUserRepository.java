package com.example.mfu.repository;

import com.example.mfu.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    public AppUser findByUsername(String username);
    public AppUser findByEmail(String email);
}