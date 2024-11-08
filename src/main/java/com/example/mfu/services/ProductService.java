package com.example.mfu.services;

import com.example.mfu.entities.Category;
import com.example.mfu.entities.Product;
import com.example.mfu.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productDao;

    public ResponseEntity<List<Product>>getAllProducts() {
        try {
            return new ResponseEntity<>(productDao.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public List<Product> getProductsByCategory(Category category) {
        return productDao.findByCategory(category);
    }

    public String createProduct(Product product) {
        productDao.save(product);
        return "success";
    }

    public String updateProduct(Product product) {
        productDao.save(product);
        return "updated";
    }

    public String delete(Integer id) {
        productDao.deleteById(id);
        return "deleted";
    }

    public Product getProductById(Integer id) {
        return productDao.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}