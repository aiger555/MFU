package com.example.mfu.controllers;

import ch.qos.logback.core.model.Model;
import com.example.mfu.entities.Category;
import com.example.mfu.entities.Product;
import com.example.mfu.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

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


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update/{id}")
    public String updateProduct(@PathVariable Integer id, @RequestBody Product product){
        // Find the existing product by ID
        Product existingq = productService.getProductById(id);

        //Update the fields
        existingq.setCategory(product.getCategory());
        existingq.setAge(product.getAge());
        existingq.setBrend(product.getBrend());
        existingq.setPrice(product.getPrice());
        existingq.setTitle(product.getTitle());
        existingq.setDetails(product.getDetails());
        existingq.setSkinProblem(product.isSkinProblem());

        //Save the updated question
        return productService.updateProduct(existingq);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
        return productService.delete(id);
    }

}
