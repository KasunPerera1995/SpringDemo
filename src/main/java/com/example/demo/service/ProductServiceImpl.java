package com.example.demo.service;

import com.example.demo.data.Product;
import com.example.demo.data.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductServiceImpl implements ProductService {

    @Autowired ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Override
    public Product getProduct(int id) {

        Product result =  RestPreconditions.checkFound(productRepository.findById(id));

        logger.debug("Received Product: {}",result);

        return result;

    }

    @Override
    public Product createProduct(Product product) {

        int id = product.getProduct_id();
        RestPreconditions.checkInsert(productRepository.findById(id));

        Product new_product = new Product();
        new_product.setProduct_id(product.getProduct_id());
        new_product.setProduct_name(product.getProduct_name());
        new_product.setPrice(product.getPrice());
        new_product.setProduct_details(product.getProduct_details());

        Product result = productRepository.save(new_product);

        logger.debug("Created Product: {}",result);

        return result;
    }

    @Override
    public Product updateProduct(int id, Product product) {


        Product updated_product = RestPreconditions.checkFound(productRepository.findById(id));

        updated_product.setProduct_name(product.getProduct_name());
        updated_product.setPrice(product.getPrice());
        updated_product.setProduct_details(product.getProduct_details());
        Product result = productRepository.save(updated_product);

        logger.debug("Updated Product: {}",result);

        return result;
    }

    @Override
    public void deleteProduct(int id) {
        Product product = RestPreconditions.checkFound(productRepository.findById(id));
        productRepository.delete(product);
        logger.debug("Product deletion successful");
    }

    @Override
    public List<Product> getProducts() {
        List<Product> result = productRepository.findAll();
        logger.debug("Return all products amount: {}",result.size());
        return result;
    }


}
