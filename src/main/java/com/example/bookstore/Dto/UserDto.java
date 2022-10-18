package com.example.bookstore.Dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    String fullName;
    @NotEmpty(message = "mobile number of user can't be empty")
    Long mobileNumber;
    String userName;
    @NotNull(message = "password required from user")
    String password;
    String email;
    String message;
}
