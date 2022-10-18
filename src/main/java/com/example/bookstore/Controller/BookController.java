package com.example.bookstore.Controller;

import com.example.bookstore.Dto.BookDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Model.BookData;
import com.example.bookstore.Service.IBookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class BookController {

    @Autowired
    IBookServiceInterface iBookServiceInterface;

    @PostMapping("/get")
    public String getMessage() {
        return "Welcome to Book Store App";
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto> saveData(@RequestBody BookDto bookDto) {
        ResponseDto responseDto = new ResponseDto("Data saved successfully", iBookServiceInterface.saveAll(bookDto));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);

    }

    @GetMapping("/data")
    public ResponseEntity<ResponseDto> displayAll() {
        ResponseDto responseDto = new ResponseDto("Book Data displayed", iBookServiceInterface.displayAll());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable int id) {
        BookData bookData = iBookServiceInterface.findById(id);
        ResponseDto responseDto = new ResponseDto("Data founded by id", bookData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<ResponseDto> updateById(@PathVariable int bookId, @RequestBody BookDto bookDto) {
        BookData bookData = iBookServiceInterface.updateById(bookId, bookDto);
        ResponseDto responseDto = new ResponseDto("Data updated by ID", bookData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/updateQuantityById/{bookId}/{bookQuantity}")
    public ResponseEntity<ResponseDto> updateQuantity(@PathVariable int bookId, @PathVariable int bookQuantity) {
        ResponseDto responseDto = new ResponseDto("Book data updated by id successfully", iBookServiceInterface.updateQuantity(bookId, bookQuantity));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable int id) {
        Boolean bookData = iBookServiceInterface.deleteById(id);
        String message = bookData ? "Data deleted successfully" : "Id not founded";
        ResponseDto responseDto = new ResponseDto(message);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = {"/bookName"})
    public ResponseEntity<ResponseDto> sortByBookName() {
        List<BookData> bookDataList = iBookServiceInterface.sortByBookName();
        ResponseDto responseDto = new ResponseDto("Book data sorted by Book name", bookDataList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = {"/Price"})
    public ResponseEntity<ResponseDto> sortByPriceAscending() {
        List<BookData> bookDataList = iBookServiceInterface.sortByPriceAscending();
        ResponseDto responseDto = new ResponseDto("Book data sorted by Book price", bookDataList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = {"/Price1"})
    public ResponseEntity<ResponseDto> sortByPriceDescending() {
        List<BookData> bookDataList = iBookServiceInterface.sortByPriceDescending();
        ResponseDto responseDto = new ResponseDto("Book data sorted by Book price", bookDataList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}