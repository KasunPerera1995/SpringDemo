package com.example.demo.controller.v1;

import com.example.demo.data.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class BackEnd {

    @Autowired
    ProductService productService;


    @GetMapping
    public ResponseEntity<List<Product>> index(){
        return ResponseEntity.ok(productService.getProducts());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> show(@PathVariable String id){

        int product_id = Integer.parseInt(id);
        return ResponseEntity.ok(productService.getProduct(product_id));

    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product resource) {

        Product createdProduct = productService.createProduct(resource);

//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(createdProduct.getProduct_id())
//                .toUri();
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
//        return ResponseEntity.created(uri).body(createdProduct);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable( "id" ) int id, @Valid @RequestBody Product resource) {

        Product updatedProduct = productService.updateProduct(id, resource);

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {

        productService.deleteProduct(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
