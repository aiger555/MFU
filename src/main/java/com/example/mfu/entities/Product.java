package com.example.mfu.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private Category category;
    private Integer age;
    private String details; // состав и воздействие на кожу.
    private String brend;
    private long price;
    private boolean skinProblem;
}
