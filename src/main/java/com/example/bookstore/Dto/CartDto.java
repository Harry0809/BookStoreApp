package com.example.bookstore.Dto;

import com.example.bookstore.Model.BookData;
import com.example.bookstore.Model.UserData;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    public Integer bookQuantity;
    Integer bookId;
}
