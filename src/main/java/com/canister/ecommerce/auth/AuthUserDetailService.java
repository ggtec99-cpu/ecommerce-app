package com.canister.ecommerce.auth;

import lombok.AllArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.net.ContentHandler;

@Service
@AllArgsConstructor
public class AuthUserDetailService implements UserDetailsService {
    private final AuthUserRepository authUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final String emailRegex = "([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}";
            AuthUserModel user;
        if (username.matches(emailRegex)){
            user = authUserRepository.findByEmail(username).orElseThrow();
        }else {
             user = authUserRepository.findByUsername(username).orElseThrow();
        }
        return user;
    }
}
