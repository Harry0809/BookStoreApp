package com.example.bookstore.Repository;

import com.example.bookstore.Model.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<CartData, Integer> {
    @Query(value = "SELECT * FROM bookStore.cart_data WHERE user_id= :userId",nativeQuery = true)
    List<CartData> findCartData(int userId);

    @Query(value = "SELECT * FROM bookStore.cart_data WHERE user_id= :userId", nativeQuery = true)
    List<CartData> findUsingUserId(Integer userId);
}
