package com.flamengo.ecommerce.controllers;

import com.flamengo.ecommerce.dtos.CategoryDTO;
import com.flamengo.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
     @Autowired
    private CategoryService categoryService;

     @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
         List<CategoryDTO> list=categoryService.findAll();
         return  ResponseEntity.ok(list);
     }
}
