package com.example.bookstore.Repository;

import com.example.bookstore.Model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrderData, Integer> {
    @Query(value = "SELECT * FROM bookStore.order_data WHERE user_id= :userId", nativeQuery = true)
    List<OrderData> findOrderData(int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bookStore.order_data SET cancel=true WHERE order_id= :orderId", nativeQuery = true)
    void updateCancel(int orderId);
}
