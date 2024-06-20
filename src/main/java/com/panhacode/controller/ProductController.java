package com.panhacode.controller;

import com.panhacode.entity.Product;
import com.panhacode.exception.ResourceNotFoundException;
import com.panhacode.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }
}
