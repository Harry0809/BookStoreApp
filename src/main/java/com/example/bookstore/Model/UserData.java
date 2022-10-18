package com.example.bookstore.Model;

import com.example.bookstore.Dto.UserDto;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer userId;
    String fullName;
    Long mobileNumber;
    String token;
    String userName;
    String password;
    String email;
    String message;

    public UserData(UserDto userDto) {
        this.userId = userId;
        this.userName = userDto.getUserName();
        this.message = userDto.getMessage();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.fullName = userDto.getFullName();
        this.mobileNumber = userDto.getMobileNumber();

    }
}
