package com.example.mfu.services;

import com.example.mfu.entities.Category;
import com.example.mfu.entities.Product;
import com.example.mfu.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ProductService {
    @Autowired
    ProductRepository productDao;

    @Value("${file.upload-dir}")
    private String uploadDir;


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

    public Product setFavorite(int productId, boolean favorite) {
        Product product = productDao.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setFavorite(favorite);
        return productDao.save(product);
    }

    public List<Product> getFavoriteProducts() {
        return productDao.findByFavorite(true);
    }


    public String uploadPhoto(Integer productId, MultipartFile file) {
        try {
            Product product = productDao.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            String contentType = file.getContentType();
            if (contentType == null || !(contentType.equals("image/png") || contentType.equals("image/jpeg"))) {
                throw new RuntimeException("Only PNG and JPEG files are allowed");
            }

            File dir = new File(uploadDir + "/" + productId);
            if (!dir.exists()) dir.mkdirs();

            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID() + "_" + originalFilename;
            File destinationFile = new File(dir, fileName);
            file.transferTo(destinationFile);

            String photoPath = dir.getPath() + "/" + fileName;
            product.getPhotos().add(photoPath);
            productDao.save(product);

            return photoPath;

        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }
}