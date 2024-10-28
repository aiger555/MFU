package com.example.mfu.dao;

import com.example.mfu.entities.Category;
import com.example.mfu.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(Category category);

    @Query(value = "SELECT * FROM product p WHERE p.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Product> findRandomProductByCategory(Category category, int numQ);
}