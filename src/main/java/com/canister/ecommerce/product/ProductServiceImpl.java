package com.canister.ecommerce.product;

import com.canister.ecommerce.product.dto.ProductCreateDto;
import com.canister.ecommerce.product.dto.ProductResponseDto;
import com.canister.ecommerce.product.dto.ProductUpdateDto;
import com.canister.ecommerce.util.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProductServiceImpl {
    ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAllProducts();

    ResponseEntity<ApiResponse<ProductResponseDto>> getProductById(UUID id);

    ResponseEntity<ApiResponse<ProductResponseDto>> createProduct(ProductCreateDto productCreateDto);

    ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(UUID id, ProductUpdateDto productUpdateDto);

    ResponseEntity<ApiResponse<ProductResponseDto>> deleteProduct(UUID id);
}
