package com.example.demo.service;

import com.example.demo.data.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(int id);
    Product createProduct(Product product);
    Product updateProduct(int id, Product product);
    void deleteProduct(int id);
    List<Product> getProducts();
}