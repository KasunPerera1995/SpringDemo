package com.example.demo.service;

import com.example.demo.data.Product;
import com.example.demo.data.ProductRepository;
import com.example.demo.mapper.Mapper;
import com.example.demo.mapper.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductServiceImpl implements ProductService {

    @Autowired ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Override
    public ProductDTO getProduct(int id) throws IllegalAccessException {

        logger.debug("Started getProduct() for request: {}",id)
        Product result =  RestPreconditions.checkFound(productRepository.findById(id));

        logger.debug("Received Product: {}",result);

        return Mapper.automaticMapper(result,new ProductDTO());

    }

    @Override
    public ProductDTO createProduct(Product product) throws IllegalAccessException {

        logger.debug("Started createProduct() for request: {}", product);
        int id = product.getProduct_id();
        RestPreconditions.checkInsert(productRepository.findById(id));

        Product new_product = new Product();
        new_product.setProduct_id(product.getProduct_id());
        new_product.setProduct_name(product.getProduct_name());
        new_product.setPrice(product.getPrice());
        new_product.setProduct_details(product.getProduct_details());

        Product result = productRepository.save(new_product);

        logger.debug("Created Product: {}",result);

        return Mapper.automaticMapper(result,new ProductDTO());
    }

    @Override
    public ProductDTO updateProduct(int id, Product product) throws IllegalAccessException {

        logger.debug("Started updateProduct() for request: {} {}",id,product);
        Product updated_product = RestPreconditions.checkFound(productRepository.findById(id));

        updated_product.setProduct_name(product.getProduct_name());
        updated_product.setPrice(product.getPrice());
        updated_product.setProduct_details(product.getProduct_details());
        Product result = productRepository.save(updated_product);

        logger.debug("Updated Product: {}",result);

        return Mapper.automaticMapper(result,new ProductDTO());
    }

    @Override
    public void deleteProduct(int id) {
        logger.debug("Started delete() for request: {}",id);
        Product product = RestPreconditions.checkFound(productRepository.findById(id));
        productRepository.delete(product);
        logger.debug("Product deletion successful");
    }

    @Override
    public List<ProductDTO> getProducts() throws IllegalAccessException {
        logger.debug("Started getProducts()");
        List<Product> products = productRepository.findAll();
        logger.debug("Return all products amount: {}",products.size());
        List<ProductDTO> list = new ArrayList<>();
        for (Product item : products) {
            ProductDTO productDTO = Mapper.automaticMapper(item, new ProductDTO());
            list.add(productDTO);
        }
        return list;
    }


}
