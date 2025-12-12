package com.canister.ecommerce.util;

public record ApiResponse<T>(
        T data,
        String message,
        boolean success
) {
}
