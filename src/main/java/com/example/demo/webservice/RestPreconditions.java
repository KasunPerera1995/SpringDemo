package com.example.demo.webservice;

import com.example.demo.error.BadRequestException;
import com.example.demo.error.MyResourceNotFoundException;

public class RestPreconditions {

    public static <T> T checkFound(T resource) {
        if (resource == null) {
            throw new MyResourceNotFoundException();
        }
        return resource;
    }

    public static <T> void checkInsert(T resource) {
        if (resource != null) {
            throw new BadRequestException("User exists");
        }
    }
}
