package com.example.demo.service;

import com.example.demo.data.Product;
import com.example.demo.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired ProductRepository productRepository;

    @Override
    public Product getProduct(int id) {
        return RestPreconditions.checkFound(productRepository.findById(id).orElse(null));
    }

    @Override
    public Product createProduct(Product product) {

        int id = product.getProduct_id();
        RestPreconditions.checkInsert(productRepository.findById(id).orElse(null));

        Product new_product = new Product();
        new_product.setProduct_id(product.getProduct_id());
        new_product.setProduct_name(product.getProduct_name());
        new_product.setPrice(product.getPrice());
        new_product.setProduct_details(product.getProduct_details());

        return productRepository.save(new_product);

    }

    @Override
    public Product updateProduct(int id, Product product) {

        Product updated_product = RestPreconditions.checkFound(productRepository.findById(id).orElse(null));

        updated_product.setProduct_name(product.getProduct_name());
        updated_product.setPrice(product.getPrice());
        updated_product.setProduct_details(product.getProduct_details());

        return productRepository.save(updated_product);
    }

    @Override
    public void deleteProduct(int id) {
        Product product = RestPreconditions.checkFound(productRepository.findById(id).orElse(null));
        productRepository.delete(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
