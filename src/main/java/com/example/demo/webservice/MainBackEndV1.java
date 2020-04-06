package com.example.demo.webservice;

import com.example.demo.data_access.Product;
import com.example.demo.data_access.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class MainBackEndV1 {

    @Autowired
    ProductRepository productRepository;


    @GetMapping("/products")
    public List<com.example.demo.data_access.Product> index(){
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Product show(@PathVariable String id){
        int product_id = Integer.parseInt(id);

        return RestPreconditions.checkFound(productRepository.findById(product_id).orElse(null));
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product resource) {
        int id = resource.getProduct_id();
        RestPreconditions.checkInsert(productRepository.findById(id).orElse(null));
        Product new_product = new Product();
        new_product.setProduct_id(resource.getProduct_id());
        new_product.setProduct_name(resource.getProduct_name());
        new_product.setPrice(resource.getPrice());
        new_product.setProduct_details(resource.getProduct_details());
        return productRepository.save(new_product);
    }

    @PutMapping(value = "/product/{id}")
    public Product update(@PathVariable( "id" ) int id, @Valid @RequestBody Product resource) {
        Product product = RestPreconditions.checkFound(productRepository.findById(id).orElse(null));
        product.setProduct_name(resource.getProduct_name());
        product.setPrice(resource.getPrice());
        product.setProduct_details(resource.getProduct_details());
        return productRepository.save(product);
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        Product product = RestPreconditions.checkFound(productRepository.findById(id).orElse(null));
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}
