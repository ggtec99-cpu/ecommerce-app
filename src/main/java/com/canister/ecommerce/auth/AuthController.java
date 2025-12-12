package com.canister.ecommerce.auth;

import com.canister.ecommerce.auth.dto.AuthUserCreateDto;
import com.canister.ecommerce.auth.dto.AuthUserEmailLoginDto;
import com.canister.ecommerce.auth.dto.AuthUserTokenResponseDto;
import com.canister.ecommerce.auth.dto.AuthUserUsernameLoginDto;
import com.canister.ecommerce.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/login/email")
    public ResponseEntity<ApiResponse<AuthUserTokenResponseDto>> userLoginByEmail(@RequestBody @Valid AuthUserEmailLoginDto emailLoginDto){
        return authService.loginUserByEmail(emailLoginDto);
    }

    @PostMapping("/login/username")
    public ResponseEntity<ApiResponse<AuthUserTokenResponseDto>> userLoginByEmail(@RequestBody @Valid AuthUserUsernameLoginDto userUsernameLoginDto){
        return authService.loginUserByUsername(userUsernameLoginDto);
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthUserTokenResponseDto>> userRegister(@RequestBody @Valid AuthUserCreateDto authUserCreateDto){
        return authService.registerUser(authUserCreateDto);
    }
}
