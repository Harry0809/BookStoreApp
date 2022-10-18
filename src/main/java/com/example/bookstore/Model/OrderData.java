package com.example.bookstore.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    String orderAddress;
    Integer bookPrice;
    Integer bookQuantity;
    LocalDate orderDate = LocalDate.now();
    @ManyToMany
    private List<BookData> bookData;
    private Boolean cancel;
    public Integer userId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @org.hibernate.annotations.ForeignKey(name = "none")
    public List<CartData> cartId;

    public OrderData(Integer userData, List<BookData> bookData, List<CartData> cartData, int totalBookQuantity, int totalBookPrice, String email, String orderAddress, boolean cancel) {
        this.bookData = bookData;
        this.orderAddress = orderAddress;
        this.orderDate = LocalDate.now();
        this.cartId = cartData;
        this.bookQuantity = totalBookQuantity;
        this.bookPrice = totalBookPrice;
        this.cancel = cancel;
        this.userId = userData;
    }
}

