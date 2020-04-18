package com.example.demo.service;

import com.example.demo.data.Product;
import com.example.demo.mapper.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO getProduct(int id) throws IllegalAccessException;
    ProductDTO createProduct(Product product) throws IllegalAccessException;
    ProductDTO updateProduct(int id, Product product) throws IllegalAccessException;
    void deleteProduct(int id);
    List<ProductDTO> getProducts() throws IllegalAccessException;
}