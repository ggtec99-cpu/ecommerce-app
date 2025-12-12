package com.canister.ecommerce.auth;

import com.canister.ecommerce.auth.dto.AuthUserCreateDto;
import com.canister.ecommerce.auth.dto.AuthUserEmailLoginDto;
import com.canister.ecommerce.auth.dto.AuthUserTokenResponseDto;
import com.canister.ecommerce.auth.dto.AuthUserUsernameLoginDto;
import com.canister.ecommerce.util.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements AuthServiceImpl {
    private final AuthUserRepository authUserRepository;
    private final AuthModelMapper authModelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;


    @Override
    public ResponseEntity<ApiResponse<AuthUserTokenResponseDto>> registerUser(AuthUserCreateDto authUserCreateDto) {
        var authUser = authModelMapper.toAuthUserModel(authUserCreateDto);

        authUser.setPassword(passwordEncoder.encode(authUserCreateDto.getPassword()));

        authUserRepository.save(authUser);

        var userId = authUser.getId().toString();
        var auth = new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword(), authUser.getAuthorities());
        authenticationManager.authenticate(auth);

        String jwtToken = jwtConfig.generateAuthToken(userId);
        var authToken = new AuthUserTokenResponseDto(jwtToken);
        var apiResponse = new ApiResponse<AuthUserTokenResponseDto>(authToken, "User Register Successfully", true);
        var header = new HttpHeaders();
        header.setBearerAuth(jwtToken);
        return new ResponseEntity<>(apiResponse, header, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<ApiResponse<AuthUserTokenResponseDto>> loginUserByUsername(AuthUserUsernameLoginDto authUserUsernameLoginDto) {
        var authUser = authUserRepository.findByUsername(authUserUsernameLoginDto.getUsername()).orElseThrow();


        if (!passwordEncoder.matches(authUserUsernameLoginDto.getPassword(), authUser.getPassword())) {
            throw new RuntimeException();
        }

        var userId = authUser.getId().toString();
        var auth = new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword(), authUser.getAuthorities());
        authenticationManager.authenticate(auth);

        String jwtToken = jwtConfig.generateAuthToken(userId);
        var authToken = new AuthUserTokenResponseDto(jwtToken);
        var apiResponse = new ApiResponse<AuthUserTokenResponseDto>(authToken, "User Login Successfully", true);
        var header = new HttpHeaders();
        header.setBearerAuth(jwtToken);
        return new ResponseEntity<>(apiResponse, header, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<ApiResponse<AuthUserTokenResponseDto>> loginUserByEmail(AuthUserEmailLoginDto authUserEmailLoginDto) {
        var authUser = authUserRepository.findByEmail(authUserEmailLoginDto.getEmail()).orElseThrow();


        if (!passwordEncoder.matches(authUserEmailLoginDto.getPassword(), authUser.getPassword())) {
            throw new RuntimeException();
        }

        var userId = authUser.getId().toString();
        var auth = new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword(), authUser.getAuthorities());
        authenticationManager.authenticate(auth);

        String jwtToken = jwtConfig.generateAuthToken(userId);
        var authToken = new AuthUserTokenResponseDto(jwtToken);
        var apiResponse = new ApiResponse<AuthUserTokenResponseDto>(authToken, "User Login Successfully", true);
        var header = new HttpHeaders();
        header.setBearerAuth(jwtToken);
        return new ResponseEntity<>(apiResponse, header, HttpStatus.CREATED);

    }

}