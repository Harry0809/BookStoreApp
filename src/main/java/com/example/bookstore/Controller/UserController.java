package com.example.bookstore.Controller;

import com.example.bookstore.Dto.LoginDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Dto.UserDto;
import com.example.bookstore.Model.UserData;
import com.example.bookstore.Service.IUserInterface;
import com.example.bookstore.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    IUserInterface iUserInterface;

    @GetMapping("/get")
    public String message() {
        return "welcome to user control";
    }

    @PostMapping("/saveAll")
    public ResponseEntity<ResponseDto> saveData(@RequestBody UserDto userDto) {
        ResponseDto responseDto = new ResponseDto("Data saved successfully", iUserInterface.saveData(userDto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @PostMapping("/userLogin")
    public ResponseEntity<ResponseDto> userLogin(@RequestBody LoginDto loginDto) {
        ResponseDto responseDto = new ResponseDto("User login successfully", iUserInterface.userLogin(loginDto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/display")
    public ResponseEntity<ResponseDto> displayData() {
        ResponseDto responseDto = new ResponseDto("User data displayed successfully", iUserInterface.displayAll());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/find1/{userId}")
    public ResponseEntity<ResponseDto> findData(@PathVariable int userId) {
        ResponseDto responseDto = new ResponseDto("user data founded by id ", iUserInterface.findById(userId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/updateById/{userId}")
    public ResponseEntity<ResponseDto> updateById(@PathVariable int userId, @RequestBody UserDto userDto) {
        ResponseDto responseDto = new ResponseDto("user data updated by id", iUserInterface.updateById(userId, userDto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{userId}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int userId) {
        Boolean userData = iUserInterface.deleteById(userId);
        String message = userData ? "UserData deleted successfully" : "userId not founded";
        ResponseDto responseDto = new ResponseDto(message);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/findByFullName/{fullName}")
    public ResponseEntity<ResponseDto> findByFullName(@PathVariable String fullName) {
        List<UserData> userDataList;
        userDataList = iUserInterface.findByFullName(fullName);
        ResponseDto responseDto = new ResponseDto("User data founded by full Name", userDataList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/userName")
    public ResponseEntity<ResponseDto> sortByUserName() {
        List<UserData> userDataList = iUserInterface.sortByUserName();
        ResponseDto responseDto = new ResponseDto("User data sorted by userName", userDataList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
