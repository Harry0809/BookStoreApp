package com.example.bookstore.Dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {

    String bookName;
    String authorName;
    @NotNull(message = "Book price can not be empty")
    Double bookPrice;
    @NotEmpty(message = "profile pic must be there")
    String profilePic;
    Integer bookQuantity;
    String message;
}
