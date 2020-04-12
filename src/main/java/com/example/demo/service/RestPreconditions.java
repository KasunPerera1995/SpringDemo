package com.example.demo.service;

import com.example.demo.error.BadRequestException;
import com.example.demo.error.MyResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class RestPreconditions {

    private static final Logger logger = LoggerFactory.getLogger(RestPreconditions.class);

    public static <T> T checkFound(Optional<T> resource) {
        if (!resource.isPresent()) {
            logger.error("Resource not Found");
            throw new MyResourceNotFoundException();
        }
        return resource.get();
    }

    public static <T> void checkInsert(Optional<T> resource) {
        if (resource.isPresent()) {
            logger.error("Insert Failed");
            throw new BadRequestException("User Exists");
        }
    }
}
