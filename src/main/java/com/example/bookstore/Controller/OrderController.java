package com.example.bookstore.Controller;

import com.example.bookstore.Dto.OrderDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    IOrderService iOrderService;

    @PostMapping("/createOrder/{token}")
    public ResponseEntity<ResponseDto> placeOrder(@RequestBody OrderDto orderDto, @PathVariable String token) {
        ResponseDto responseDto = new ResponseDto("Order placed successfully", iOrderService.placeOrder(orderDto, token));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<ResponseDto> getAllOrder() {
        ResponseDto responseDto = new ResponseDto("Order data retrieved successfully", iOrderService.getAll());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getOrderById/{orderId}")
    public ResponseEntity<ResponseDto> getOrderById(@PathVariable int orderId) {
        ResponseDto responseDto = new ResponseDto("Order data by order id retrieved successfully", iOrderService.getOrderById(orderId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/orderDataByUserId/{token}")
    public ResponseEntity<ResponseDto> getByUserId(@PathVariable String token) {
        ResponseDto responseDto = new ResponseDto("order retrieved with userId successfully", iOrderService.getByUserId(token));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable int orderId) {
        ResponseDto responseDto = new ResponseDto("Order cancelled successfully", iOrderService.cancelOrder(orderId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
