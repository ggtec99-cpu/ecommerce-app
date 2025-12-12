package com.canister.ecommerce.product.exception;

import com.canister.ecommerce.util.HttpException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends HttpException {
    public ProductNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND,message);
    }

}
