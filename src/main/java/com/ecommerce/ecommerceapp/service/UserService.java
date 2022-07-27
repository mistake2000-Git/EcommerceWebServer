package com.ecommerce.ecommerceapp.service;

import com.ecommerce.ecommerceapp.config.MessageStrings;
import com.ecommerce.ecommerceapp.dto.User.SignInDto;
import com.ecommerce.ecommerceapp.dto.User.SignInReponseDto;
import com.ecommerce.ecommerceapp.dto.User.SignUpDto;
import com.ecommerce.ecommerceapp.dto.User.SignUpResponseDto;
import com.ecommerce.ecommerceapp.exceptions.AuthenticationFailException;
import com.ecommerce.ecommerceapp.exceptions.CustomException;
import com.ecommerce.ecommerceapp.model.AuthenticationToken;
import com.ecommerce.ecommerceapp.model.User;
import com.ecommerce.ecommerceapp.repository.TokenRepository;
import com.ecommerce.ecommerceapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationService authenticationService;
    Logger logger = LoggerFactory.getLogger(UserService.class);
    public SignUpResponseDto signUp(SignUpDto signUpDto) throws CustomException {
        if(Objects.nonNull(userRepository.findUserByEmail(signUpDto.getEmail()))){
            throw new CustomException("User Already Exist");
        }
        String encryptedPassword = signUpDto.getPassword();
        try
        {
            encryptedPassword = hashPassword(signUpDto.getPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }
        User user = new User(signUpDto.getFirstName(),signUpDto.getLastName(),signUpDto.getEmail(),encryptedPassword);
        try
        {
            userRepository.save(user);
            final AuthenticationToken authenticationToken = new AuthenticationToken(user);
            authenticationService.saveConfirmInformationToken(authenticationToken);
            return new SignUpResponseDto("User created successfully","success");
        }catch (Exception e)
        {
            throw new CustomException(e.getMessage());
        }
    }
    String hashPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
    public SignInReponseDto signIn(SignInDto signInDto) throws AuthenticationFailException,CustomException
    {
         User user = userRepository.findUserByEmail(signInDto.getEmail());
         if(!Objects.nonNull(user))
         {
             throw new AuthenticationFailException("User not exist");
         }
         try
         {
             if(!user.getPassword().equals(hashPassword(signInDto.getPassword())))
             {
                 throw new  AuthenticationFailException(MessageStrings.AUTH_EMAIL_OR_PASSWORD_NOT_VALID);
             }
         }catch (NoSuchAlgorithmException e)
         {
             e.printStackTrace();
             logger.error("hashin password failed {}",e.getMessage());
             throw new CustomException(e.getMessage());
         }

         AuthenticationToken authenticationToken = authenticationService.getUserToken(user);
         if(!Objects.nonNull(authenticationToken))
         {
             throw new CustomException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
         }
         return new SignInReponseDto("Sign in successfully",authenticationToken.getToken());

    }

}
