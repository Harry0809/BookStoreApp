package com.example.bookstore.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    public String email;
    public String password;
}
