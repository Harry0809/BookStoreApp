package com.example.bookstore.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer cartId;
    @OneToOne
    @JoinColumn(name = "user_id")
    public UserData userId;
    @ManyToOne
    @JoinColumn(name = "book_id")
    public BookData bookId;
    public Integer bookQuantity;
    double bookPrice;

    public CartData(UserData userData, BookData bookData, Integer bookQuantity, double bookPrice) {
        this.userId = userData;
        this.bookId = bookData;
        this.bookQuantity = bookQuantity;
        this.bookPrice = bookPrice;
    }


}


