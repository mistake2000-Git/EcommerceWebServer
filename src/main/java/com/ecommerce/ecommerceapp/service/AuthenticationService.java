package com.ecommerce.ecommerceapp.service;

import com.ecommerce.ecommerceapp.config.MessageStrings;
import com.ecommerce.ecommerceapp.exceptions.AuthenticationFailException;
import com.ecommerce.ecommerceapp.model.AuthenticationToken;
import com.ecommerce.ecommerceapp.model.User;
import com.ecommerce.ecommerceapp.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import javax.naming.AuthenticationException;
import java.util.Objects;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;
    //save the confirmation token
    public void saveConfirmInformationToken(AuthenticationToken authenticationToken)
    {
        tokenRepository.save(authenticationToken);
    }

    //get user of token
    public AuthenticationToken getUserToken(User user)
    {
        return tokenRepository.findAuthenticationTokenByUser(user);
    }
    // Get user from token
    public User getTokenUser(String token)
    {
        AuthenticationToken authenticationToken = tokenRepository.findAuthenticationTokenByToken(token);
        if(Objects.nonNull(authenticationToken))
        {
            if(Objects.nonNull(authenticationToken.getUser())){
                return authenticationToken.getUser();
            }
        }
        return null;
    }
    //check valid token
    public void authenticate(String token) throws  AuthenticationFailException {
        if(!Objects.nonNull(token))
        {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }
        if(!Objects.nonNull(getTokenUser(token)))
        {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_VALID);
        }

    }

}
