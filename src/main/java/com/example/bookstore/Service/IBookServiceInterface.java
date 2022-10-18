package com.example.bookstore.Service;

import com.example.bookstore.Dto.BookDto;
import com.example.bookstore.Model.BookData;

import java.util.List;

public interface IBookServiceInterface {
    Object saveAll(BookDto bookDto);

    Object displayAll();

    BookData findById(int id);

    BookData updateById(int bookId, BookDto bookDto);

    Boolean deleteById(int id);

    List<BookData> sortByBookName();

    List<BookData> sortByPriceAscending();

    List<BookData> sortByPriceDescending();

    Object updateQuantity(int bookId, int bookQuantity);
}
