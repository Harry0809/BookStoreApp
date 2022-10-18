package com.example.bookstore.Model;

import com.example.bookstore.Dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookData {
    String bookName;
    String authorName;
    Double bookPrice;
    String profilePic;
    Integer bookQuantity;

    String message;

    public BookData(String message) {
        this.message = message;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    public BookData(BookDto bookDto) {
        this.bookId = bookId;
        this.bookName = bookDto.getBookName();
        this.bookQuantity = bookDto.getBookQuantity();
        this.bookPrice = bookDto.getBookPrice();
        this.authorName = bookDto.getAuthorName();
        this.profilePic = bookDto.getProfilePic();
        this.message = bookDto.getMessage();

    }
}
