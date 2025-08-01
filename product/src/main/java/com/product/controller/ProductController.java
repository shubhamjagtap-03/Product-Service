package com.product.controller;



import com.product.dto.ProductDto;
import com.product.entity.Product;
import com.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String name) {
        return ResponseEntity.ok(productRepository.findAll().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .toList());
    }

    
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct( @RequestBody ProductDto dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return ResponseEntity.ok(productRepository.save(product));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto dto) {
        return productRepository.findById(id)
                .map(existing -> {
                    BeanUtils.copyProperties(dto, existing);
                    existing.setId(id);
                    return ResponseEntity.ok(productRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.ok(product);  // Return the deleted product
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
