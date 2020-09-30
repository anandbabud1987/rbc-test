package com.rbc.assignment.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.nonNull;

@Service
public class ServiceUtil {
    public void validate(MultipartFile file) {
        if (!nonNull(file)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Files is mandatory");

        }
    }

    public void validateStock(String stock) {
        if (!nonNull(stock)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "stock is mandatory");

        }
    }
}
