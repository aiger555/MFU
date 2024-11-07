package com.example.mfu.controllers;

import com.example.mfu.entities.AppUser;
import com.example.mfu.entities.Category;
import com.example.mfu.entities.Product;
import com.example.mfu.repository.AppUserRepository;
import com.example.mfu.repository.ProductRepository;
import com.example.mfu.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;


@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("category/{category}")
    public List<Product> getProductByCategory(@PathVariable Category category){
        return productService.getProductsByCategory(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("add")
    public String addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }


    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody Product product) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        AppUser currentUser = appUserRepository.findByUsername(currentUsername);

        if (currentUser == null || currentUser.getRole() != "ADMIN") {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can create products");
        }

        product.setTitle(product.getTitle());
        product.setCategory(product.getCategory());
        product.setAge(product.getAge());
        product.setDetails(product.getDetails());
        product.setBrend(product.getBrend());
        product.setPrice(product.getPrice());
        product.setSkinProblem(product.isSkinProblem());

        productRepository.save(product);

        return ResponseEntity.ok("Product created successfully");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser currentUser = appUserRepository.findByUsername(currentUsername);

        if (currentUser == null || currentUser.getRole() != "ADMIN") {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can update products");
        }

        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        existingProduct.setCategory(product.getCategory());
        existingProduct.setAge(product.getAge());
        existingProduct.setBrend(product.getBrend());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setTitle(product.getTitle());
        existingProduct.setDetails(product.getDetails());
        existingProduct.setSkinProblem(product.isSkinProblem());

        productService.updateProduct(existingProduct);
        return ResponseEntity.ok("Product updated successfully");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser currentUser = appUserRepository.findByUsername(currentUsername);

        if (currentUser == null || currentUser.getRole() != "ADMIN") {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can delete products");
        }

        boolean deleted = Boolean.parseBoolean(productService.delete(id));
        if (deleted) {
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }



}