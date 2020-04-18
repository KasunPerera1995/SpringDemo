package com.example.demo.mapper;

public class ProductDTO {
    private int product_id;
    private String product_name;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return product_name;
    }

    public void setName(String name) {
        this.product_name = name;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "product_id=" + product_id +
                ", name='" + product_name + '\'' +
                '}';
    }
}
