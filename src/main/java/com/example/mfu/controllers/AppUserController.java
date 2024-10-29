package com.example.mfu.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {
    @GetMapping("/")
    public String home() {
        return "Home page";
    }

    @GetMapping("/products")
    public String products() {
        return "Products page;";
    }

    @GetMapping("/admin/home")
    public String getAdminHome() {
        return "Admin home page;";
    }

    @GetMapping("/client/home")
    public String getClientHome() {
        return "Clent home page;";
    }
}
