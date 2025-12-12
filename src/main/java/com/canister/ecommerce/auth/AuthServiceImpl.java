package com.canister.ecommerce.auth;

import com.canister.ecommerce.auth.dto.AuthUserCreateDto;
import com.canister.ecommerce.auth.dto.AuthUserEmailLoginDto;
import com.canister.ecommerce.auth.dto.AuthUserTokenResponseDto;
import com.canister.ecommerce.auth.dto.AuthUserUsernameLoginDto;
import com.canister.ecommerce.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AuthServiceImpl {
    ResponseEntity<ApiResponse<AuthUserTokenResponseDto>> registerUser(AuthUserCreateDto authUserCreateDto);

    ResponseEntity<ApiResponse<AuthUserTokenResponseDto>> loginUserByUsername(AuthUserUsernameLoginDto authUserUsernameLoginDto);

    ResponseEntity<ApiResponse<AuthUserTokenResponseDto>> loginUserByEmail(AuthUserEmailLoginDto authUserEmailLoginDto);
}
