package com.flamengo.ecommerce.dtos;

import com.flamengo.ecommerce.entities.Product;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public void setProducts(Set<Product> products) {
        this.products = products.stream().map(
                product ->
                        new ProductDTO(product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                product.getImgUrl(),
                                product.getCategories())).toList();


    }
}