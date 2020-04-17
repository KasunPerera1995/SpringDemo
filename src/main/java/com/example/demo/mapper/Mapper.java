package com.example.demo.mapper;

import com.example.demo.data.Product;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Mapper {

    public static <D,O> D automaticMapper(O object, D dto) throws IllegalAccessException {
        Field[] obj_fields = object.getClass().getDeclaredFields();
        Field[] dto_Fields = dto.getClass().getDeclaredFields();

        for (Field obj_field : obj_fields) {
            for (Field dto_field : dto_Fields) {
                if (obj_field.getName().equals(dto_field.getName()) && obj_field.getType().equals(dto_field.getType())) {
                    obj_field.setAccessible(true);
                    dto_field.setAccessible(true);
                    dto_field.set(dto, obj_field.get(object));
                    System.out.println("sddla" + obj_field.get(object) + "\n");
                    obj_field.setAccessible(false);
                    dto_field.setAccessible(false);
                }

            }
        }
        return dto;
    }

    public static <D,O> D manual_mapper(O source, D dto, Map<String,String> mappings) throws NoSuchFieldException, IllegalAccessException {
        for (Map.Entry<String,String> entry:mappings.entrySet()) {

            Field field = source.getClass().getDeclaredField(entry.getKey());
            Field mapped_field = dto.getClass().getDeclaredField(entry.getValue());

            mapped_field.setAccessible(true);
            field.setAccessible(true);
            mapped_field.set(dto,field.get(source));
        }
        return dto;

    }
    public static void main(String [] args) throws IllegalAccessException, NoSuchFieldException {
        Product product = new Product();
        product.setProduct_id(4);
        product.setPrice(400);
        product.setProduct_name("Test");
        ProductDTO productDTO = automaticMapper(product, new ProductDTO());
        Map<String,String> mappings = new HashMap<>();
        mappings.put("product_name","name");
        manual_mapper(product,productDTO,mappings);
        System.out.println(productDTO.toString());
    }
}
