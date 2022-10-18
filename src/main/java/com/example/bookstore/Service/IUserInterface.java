package com.example.bookstore.Service;

import com.example.bookstore.Dto.LoginDto;
import com.example.bookstore.Dto.UserDto;
import com.example.bookstore.Model.UserData;

import java.util.List;

public interface IUserInterface {
    Object saveData(UserDto userDto);

    Object displayAll();

    Object findById(int userId);

    Object updateById(int userId, UserDto userDto);

    Boolean deleteById(int userId);

    List<UserData> findByFullName(String fullName);

    List<UserData> sortByUserName();

    Object userLogin(LoginDto loginDto);
}
