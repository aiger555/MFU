package com.example.mfu.controllers;

import com.example.mfu.entities.AppUser;
import com.example.mfu.entities.Category;
import com.example.mfu.entities.Product;
import com.example.mfu.repository.AppUserRepository;
import com.example.mfu.repository.CategoryRepository;
import com.example.mfu.repository.ProductRepository;
import com.example.mfu.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

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

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductByCategoryId(@PathVariable Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category not found with id " + categoryId));
        return productService.getProductsByCategory(category);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        String res = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        product.setId(id);
        String res = productService.updateProduct(product);
        return ResponseEntity.ok(res);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
        String res = productService.delete(id);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{productId}/favorite")
    public ResponseEntity<Product> setFavorite(@PathVariable int productId, @RequestParam boolean favorite) {
        Product updatedProduct = productService.setFavorite(productId, favorite);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<Product>> getFavoriteProducts() {
        List<Product> favoriteProducts = productService.getFavoriteProducts();
        return ResponseEntity.ok(favoriteProducts);
    }



}