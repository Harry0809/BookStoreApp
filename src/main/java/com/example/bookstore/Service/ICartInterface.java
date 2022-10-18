package com.example.bookstore.Service;

import com.example.bookstore.Dto.CartDto;

public interface ICartInterface {
    Object addInCart(CartDto cartDto, String token);

    Object getCartData(String token);

    Object dataById(int cartId, String token);

    Object removeItemFromCart(int cartId, String token);

    Object updateCart(String token, int quantity, int cartId);

    Object updateCartByToken(CartDto cartDto, String token, int cartId);
}
