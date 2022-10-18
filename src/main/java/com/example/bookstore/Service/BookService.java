package com.example.bookstore.Service;

import com.example.bookstore.Dto.BookDto;
import com.example.bookstore.ExceptionHandling.BookStoreException;
import com.example.bookstore.Model.BookData;
import com.example.bookstore.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookServiceInterface {

    @Autowired
    BookRepo bookRepo;

    public Object saveAll(BookDto bookDto) {
        List<BookData> bookDataList = bookRepo.findAll();
        for (BookData bookData : bookDataList) {
            if (bookData.getBookName().equals(bookDto.getBookName())) {
                throw new BookStoreException("This is duplicate value");
            }
        }
        BookData bookData1 = new BookData(bookDto);
        return bookRepo.save(bookData1);
    }

    @Override
    public List<BookData> displayAll() {
        return bookRepo.findAll();
    }

    @Override
    public BookData findById(int id) {
        return bookRepo.findById(id).orElseThrow(() -> new BookStoreException("Book data with id =" + " " + id + " " + "is not founded"));
    }

    @Override
    public BookData updateById(int bookId, BookDto bookDto) {
        Optional<BookData> bookData = bookRepo.findById(bookId);
        if (bookData.isPresent()) {
            bookData.get().setAuthorName(bookDto.getAuthorName());
            bookData.get().setBookName(bookDto.getBookName());
            bookData.get().setBookPrice(bookDto.getBookPrice());
            bookData.get().setBookQuantity(bookDto.getBookQuantity());
            bookData.get().setMessage(bookDto.getMessage());
            bookData.get().setProfilePic(bookDto.getProfilePic());
            bookRepo.save(bookData.get());
            return bookData.get();
        } else {
            throw new BookStoreException("Book Data with id = " + " " + bookId + " " + "is not founded");
        }

    }

    @Override
    public Boolean deleteById(int id) {
        Optional<BookData> bookData = bookRepo.findById(id);
        if (bookData.isPresent()) {
            bookRepo.deleteById(id);
            return true;
        } else {
            throw new BookStoreException("Book data with id = " + " " + id + " " + "is not founded");
        }
    }

    @Override
    public List<BookData> sortByBookName() {
        List<BookData> bookData = bookRepo.sortByBookName();
        return bookData;

    }

    @Override
    public List<BookData> sortByPriceAscending() {
        List<BookData> bookData = bookRepo.sortByPriceAscending();
        return bookData;
    }

    public List<BookData> sortByPriceDescending() {
        List<BookData> bookData = bookRepo.sortByPriceDescending();
        return bookData;
    }

    @Override
    public String updateQuantity(int bookId, int bookQuantity) {
        if (bookRepo.findById(bookId).isPresent()) {
            bookRepo.updateBookQuantity(bookId, bookQuantity);
            System.out.println(bookRepo.findAll());
            return "updated quantity by id successfully";
        } else {
            throw (new BookStoreException("This id is not present"));
        }

    }
}
