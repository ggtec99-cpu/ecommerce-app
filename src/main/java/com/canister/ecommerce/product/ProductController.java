package com.canister.ecommerce.product;

import com.canister.ecommerce.product.dto.ProductCreateDto;
import com.canister.ecommerce.product.dto.ProductResponseDto;
import com.canister.ecommerce.product.dto.ProductUpdateDto;
import com.canister.ecommerce.util.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.v1}/products")
@AllArgsConstructor
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAllProducts(){
        return productServiceImpl.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProductById(@PathVariable UUID id){
        return productServiceImpl.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDto>> createProduct(@RequestBody ProductCreateDto productCreateDto){
        return productServiceImpl.createProduct(productCreateDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(@PathVariable UUID id, @RequestBody ProductUpdateDto productUpdateDto){
        return productServiceImpl.updateProduct(id, productUpdateDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> deleteProduct(@PathVariable UUID id){
        return productServiceImpl.deleteProduct(id);
    }
}
