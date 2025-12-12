package com.canister.ecommerce.auth;

import com.canister.ecommerce.auth.dto.AuthUserCreateDto;
import com.canister.ecommerce.auth.dto.AuthUserEmailUpdateDto;
import com.canister.ecommerce.auth.dto.AuthUserPasswordUpdateDto;
import com.canister.ecommerce.auth.dto.AuthUserUsernameUpdateDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@Service
@AllArgsConstructor
public class AuthModelMapper {
    private final ModelMapper mapper;


    public AuthUserModel toAuthUserModel(AuthUserCreateDto authUserCreateDto){
        return mapper.map(authUserCreateDto, AuthUserModel.class);
    }

    public AuthUserModel toAuthUpdateModel(AuthUserModel authUserModel,AuthUserPasswordUpdateDto passwordUpdateDto){
        authUserModel.setPassword(passwordUpdateDto.getNewPassword());
        return authUserModel;
    }

    public AuthUserModel toAuthUpdateModel(AuthUserModel authUserModel, AuthUserEmailUpdateDto authUserEmailUpdateDto){
        authUserModel.setEmail(authUserEmailUpdateDto.getEmail());
        return authUserModel;
    }
    public AuthUserModel toAuthUpdateModel(AuthUserModel authUserModel, AuthUserUsernameUpdateDto authUserUsernameUpdateDto){
        authUserModel.setUsername(authUserUsernameUpdateDto.getUsername());
        return authUserModel;
    }
}
