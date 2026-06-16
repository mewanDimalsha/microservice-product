package com.example.productservice.service;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.model.Product;
import com.example.productservice.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts(){
        List<Product> productList = productRepo.findAll();
        return modelMapper.map(productList,new TypeToken<List<ProductDTO>>(){}.getType());
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepo.save(product);  // save() returns the saved entity with generated ID
        return modelMapper.map(savedProduct, ProductDTO.class);  // map saved entity back to DTO
    }
}
