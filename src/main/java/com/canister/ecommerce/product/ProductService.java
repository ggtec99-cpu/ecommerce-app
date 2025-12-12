package com.canister.ecommerce.product;

import com.canister.ecommerce.category.CategoryRepository;
import com.canister.ecommerce.product.dto.ProductCreateDto;
import com.canister.ecommerce.product.dto.ProductResponseDto;
import com.canister.ecommerce.product.dto.ProductUpdateDto;
import com.canister.ecommerce.product.exception.ProductNotFoundException;
import com.canister.ecommerce.util.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService implements ProductServiceImpl {
    private final ProductRepository productRepository;
    private final ProductModelMapper productModelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAllProducts(){
        var products = productRepository.findAll();
        var productResponse = productModelMapper.toProductsResponse(products);
        var apiResponse = new ApiResponse<List<ProductResponseDto>>(productResponse,"Product Successfully Fetched",true);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProductById(UUID id){
        var product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("%s not found".formatted(id.toString())));
        var productResponse = productModelMapper.toProductResponse(product);
        var apiResponse = new ApiResponse<ProductResponseDto>(productResponse,"Product Successfully Fetched",true);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse<ProductResponseDto>> createProduct(ProductCreateDto productCreateDto){
        var product = productModelMapper.toProductModel(productCreateDto);
        var category = categoryRepository.findById(UUID.fromString(productCreateDto.getCategory())).orElseThrow();
        product.setCategory(category);
        var newProduct = productRepository.save(product);
        var productResponse = productModelMapper.toProductResponse(newProduct);
        var apiResponse = new ApiResponse<ProductResponseDto>(productResponse,"Product Successfully Created",true);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(UUID id, ProductUpdateDto productUpdateDto){
        var product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("%s not found".formatted(id.toString())));
        var category = categoryRepository.findById(UUID.fromString(productUpdateDto.getCategory())).orElseThrow();
        product.setName(productUpdateDto.getName());
        product.setDescription(productUpdateDto.getDescription());
        product.setPrice(productUpdateDto.getPrice());
        product.setCategory(category);
        var productResponse = productModelMapper.toProductResponse(productRepository.save(product));
        var apiResponse = new ApiResponse<ProductResponseDto>(productResponse,"Product Successfully Updated",true);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse<ProductResponseDto>> deleteProduct(UUID id){
        var product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("%s not found".formatted(id.toString())));
        productRepository.delete(product);
        var apiResponse = new ApiResponse<ProductResponseDto>(null,"Product Successfully Deleted",true);
        return ResponseEntity.ok(apiResponse);
    }
}
