package com.example.mfu.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer age;
    private String details; // состав и воздействие на кожу.
    private String brend;
    private long price;
    private boolean skinProblem;
    private boolean favorite;

    @ElementCollection
    @CollectionTable(name = "product_photos", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "photo_path")
    private List<String> photos = new ArrayList<>();
}
