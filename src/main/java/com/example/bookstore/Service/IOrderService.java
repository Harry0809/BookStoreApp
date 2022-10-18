package com.example.bookstore.Service;

import com.example.bookstore.Dto.OrderDto;
import com.example.bookstore.Model.OrderData;

import java.util.List;

public interface IOrderService {
    Object placeOrder(OrderDto orderDto, String token);

    Object getAll();

    Object getOrderById(int orderId);

    List<OrderData> getByUserId(String token);

    String cancelOrder(int orderId);
}
