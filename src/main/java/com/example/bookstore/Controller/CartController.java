package com.example.bookstore.Controller;

import com.example.bookstore.Dto.CartDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Service.ICartInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    ICartInterface iCartInterface;

    @PostMapping("/saveInCart/{token}")
    public ResponseEntity<ResponseDto> saveInCart(@RequestBody CartDto cartDto, @PathVariable String token) {
        ResponseDto responseDto = new ResponseDto("Data saved successfully", iCartInterface.addInCart(cartDto, token));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getData/{token}")
    public ResponseEntity<ResponseDto> getCartData(@PathVariable String token) {
        ResponseDto responseDto = new ResponseDto("all cart data", iCartInterface.getCartData(token));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/cartDataById/{cartId}/{token}")
    public ResponseEntity<ResponseDto> dataById(@PathVariable int cartId, @PathVariable String token) {
        ResponseDto responseDto = new ResponseDto("Cart data founded by id", iCartInterface.dataById(cartId, token));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/removeItem/{cartId}/{token}")
    public ResponseEntity<ResponseDto> removeItemById(@PathVariable int cartId, @PathVariable String token) {
        ResponseDto responseDto = new ResponseDto("Data removed from cart", iCartInterface.removeItemFromCart(cartId, token));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/updateCart1/{bookQuantity}/{cartId}")
    public ResponseEntity<ResponseDto> updateCartByQuantity(@RequestParam String token, @PathVariable int bookQuantity, @PathVariable int cartId) {
        ResponseDto responseDto = new ResponseDto("cart data updated successfully", iCartInterface.updateCart(token, bookQuantity, cartId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/updateCart/{token}/{cartId}")
    public ResponseEntity<ResponseDto> updateCartByToken(@RequestBody CartDto cartDto, @PathVariable String token, @PathVariable int cartId) {
        ResponseDto responseDto = new ResponseDto("cart data successfully updated", iCartInterface.updateCartByToken(cartDto, token, cartId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
