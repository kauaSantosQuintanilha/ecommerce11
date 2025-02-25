package com.flamengo.ecommerce.controllers;

import com.flamengo.ecommerce.dtos.ProductDTO;
import com.flamengo.ecommerce.entities.Product;
import com.flamengo.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
            ProductDTO productDTO = productService.findById(id);
            return ResponseEntity.ok(productDTO);
    }
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> list=productService.findAll();
        return  ResponseEntity.ok(list);
    }
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO product = productService.create(productDTO);
        return ResponseEntity.ok(product);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO product = productService.update(id, productDTO);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
         productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
