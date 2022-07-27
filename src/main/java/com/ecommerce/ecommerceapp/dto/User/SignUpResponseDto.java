package com.ecommerce.ecommerceapp.dto.User;

public class SignUpResponseDto {
    private String message;
    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public SignUpResponseDto(String message, String success) {
        this.message = message;
        this.success = success;
    }
}
