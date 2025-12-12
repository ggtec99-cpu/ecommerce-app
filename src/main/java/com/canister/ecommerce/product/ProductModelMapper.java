package com.canister.ecommerce.product;

import com.canister.ecommerce.product.dto.ProductCreateDto;
import com.canister.ecommerce.product.dto.ProductResponseDto;
import com.canister.ecommerce.product.dto.ProductUpdateDto;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductModelMapper {
  private final ModelMapper mapper;

  //
  // ProductModelMapper(ModelMapper mapper){
  // this.mapper = mapper;
  // }
  public ProductModel toProductModel(ProductCreateDto productCreateDto) {
    return mapper.map(productCreateDto, ProductModel.class);
  }

  public ProductModel toProductModel(ProductUpdateDto productUpdateDto) {
    return mapper.map(productUpdateDto, ProductModel.class);
  }

  public ProductResponseDto toProductResponse(ProductModel productModel) {
    return mapper.map(productModel, ProductResponseDto.class);
  }

  public List<ProductResponseDto> toProductsResponse(List<ProductModel> productModels) {
    return productModels.stream()
        .map(this::toProductResponse)
        .collect(Collectors.toList());
  }
}
