package com.flamengo.ecommerce.service;

import com.flamengo.ecommerce.dtos.ProductDTO;
import com.flamengo.ecommerce.entities.Category;
import com.flamengo.ecommerce.entities.Product;
import com.flamengo.ecommerce.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
   @Autowired
  private ProductRepository productRepository;
   @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
       Product product =  productRepository.findById(id).orElseThrow();
       ProductDTO productDTO  = productToProductDTO(product);
       return productDTO;

    }
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> list = productRepository.findAll();
        List<Product>products = productRepository.findAll();
        return list.stream().map(product -> productToProductDTO(product)).toList();
    }
    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        Product product = productDTOToProduct(productDTO);
        Product  saveProduct= productRepository.save(product);
        return productToProductDTO(product);

    }
    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
       Product product = productDTOToProduct(productDTO);
       product.setId(id);
       //o repository não consegue salvar um DTO,ele só consegue salvar uma entidade
      product = productRepository.save(product);
      return productToProductDTO(product);

    }
    @Transactional
    public void  delete(Long id) {
      productRepository.deleteById(id);
    }


   public ProductDTO productToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImg_url(product.getImg_url());
        productDTO.setCategories((List<Category>) product.getCategories());
        return productDTO;
    }
    public Product productDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImg_url(productDTO.getImg_url());
        product.setCategories(productDTO.getCategories());
        return product;
    }



}
