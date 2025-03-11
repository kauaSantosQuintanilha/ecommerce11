package com.flamengo.ecommerce.service;

import com.flamengo.ecommerce.dtos.CategoryDTO;
import com.flamengo.ecommerce.dtos.ProductDTO;
import com.flamengo.ecommerce.entities.Category;
import com.flamengo.ecommerce.entities.Product;
import com.flamengo.ecommerce.respository.CategoryRepository;
import com.flamengo.ecommerce.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return productToProductDTO(product);

    }
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::productToProductDTO).toList();
    }
    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());

        // Buscando categorias existentes no banco antes de associar
        Set<Category> categories = productDTO.getCategories().stream()
                .map(categoryDTO -> categoryRepository.findById(categoryDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + categoryDTO.getId())))
                .collect(Collectors.toSet());

        product.setCategories(categories);

        // Salvando o produto já com as categorias corretamente associadas
        Product savedProduct = productRepository.save(product);
        return productToProductDTO(savedProduct);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO){
        Product product = productDTOToProduct(productDTO);
        product.setId(id);
        // o repository não consegue salvar um DTO, ele salva entity
        product = productRepository.save(product);
        return productToProductDTO(product);
    }

    @Transactional
    public void delete(Long id){
        productRepository.deleteById(id);
    }


    private ProductDTO productToProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl(),
                product.getCategories().stream()
                        .map(category -> new CategoryDTO(category.getId(), category.getName()))
                        .toList()
        );
    }

    private Product productDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());

        // Buscando categorias existentes no banco
        Set<Category> categories = productDTO.getCategories().stream()
                .map(categoryDTO -> categoryRepository.findById(categoryDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + categoryDTO.getId())))
                .collect(Collectors.toSet());

        product.setCategories(categories);
        return product;
    }

}
