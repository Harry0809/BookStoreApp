package com.example.bookstore.Service;

import com.example.bookstore.Dto.LoginDto;
import com.example.bookstore.Dto.UserDto;
import com.example.bookstore.ExceptionHandling.BookStoreException;
import com.example.bookstore.Model.UserData;
import com.example.bookstore.Repository.UserRepo;
import com.example.bookstore.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserInterface {
    @Autowired
    UserRepo userRepo;
    @Autowired
    TokenUtil tokenUtil;

    @Override
    public Object saveData(UserDto userDto) {
        List<UserData> userDataList = userRepo.findAll();
        for (UserData userData : userDataList) {
            if (userData.getUserName().equals(userDto.getUserName())) {
                throw new BookStoreException("This is a duplicate value : Enter different user values");
            }
        }
        UserData userData1 = new UserData(userDto);
        userRepo.save(userData1);
        String token = tokenUtil.createToken(userData1.getUserId());
        userData1.setToken(token);
        return userRepo.save(userData1);
    }

    @Override
    public Object displayAll() {
        return userRepo.findAll();
    }

    @Override
    public Object findById(int userId) {
        return userRepo.findById(userId).orElseThrow(() -> new BookStoreException("user data not available with this id=" + userId));
    }

    @Override
    public Object updateById(int userId, UserDto userDto) {
        Optional<UserData> userData = userRepo.findById(userId);
        if (userData.isPresent()) {
            userData.get().setEmail(userDto.getEmail());
            userData.get().setFullName(userDto.getFullName());
            userData.get().setMobileNumber(userDto.getMobileNumber());
            userData.get().setUserName(userDto.getUserName());
            userData.get().setMessage(userDto.getMessage());
            userData.get().setPassword(userDto.getPassword());
            userRepo.save(userData.get());
            return userData.get();
        } else {
            throw new BookStoreException("user data with id=" + " " + userId + " " + " is not founded");
        }
    }

    @Override
    public Boolean deleteById(int userId) {
        Optional<UserData> userData = userRepo.findById(userId);
        if (userData.isPresent()) {
            userRepo.deleteById(userId);
            return true;
        } else {
            throw new BookStoreException("User data to be deleted with id =" + userId + " " + "is not founded");
        }
    }

    @Override
    public List<UserData> findByFullName(String fullName) {
//        List<UserData> userData = userRepo.findByFullName(fullName);
//        if (userData.isEmpty()) {
//            throw new BookStoreException("user data founded by name");
//        } else {
//            return userData;
//        }
        return null;
    }

    @Override
    public List<UserData> sortByUserName() {
        List<UserData> userDataList = userRepo.sortByUserName();
        return userDataList;
    }

    @Override
    public String userLogin(LoginDto loginDto) {
        List<UserData> userData = userRepo.findAll();
        for (UserData user : userData) {
            if (user.getEmail().equals(loginDto.getEmail()) && user.getPassword().equals(loginDto.getPassword())) {
                return "Login succesfully";
            }

        }
        throw new BookStoreException("User data is not founded");
    }
}
