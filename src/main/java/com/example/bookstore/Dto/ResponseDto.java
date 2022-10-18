package com.example.bookstore.Dto;

import lombok.Data;

@Data
public class ResponseDto {
    String message;
    Object Data;

    public ResponseDto() {
    }
    public ResponseDto(String message) {
        this.message = message;
    }
    public ResponseDto(String message, Object data) {
        this.message = message;
        Data = data;
    }
}