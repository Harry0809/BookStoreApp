package com.example.bookstore.Service;

import com.example.bookstore.Dto.OrderDto;
import com.example.bookstore.ExceptionHandling.BookStoreException;
import com.example.bookstore.Model.BookData;
import com.example.bookstore.Model.CartData;
import com.example.bookstore.Model.OrderData;
import com.example.bookstore.Model.UserData;
import com.example.bookstore.Repository.BookRepo;
import com.example.bookstore.Repository.CartRepo;
import com.example.bookstore.Repository.OrderRepo;
import com.example.bookstore.Repository.UserRepo;
import com.example.bookstore.Util.TokenUtil;
import com.example.bookstore.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BookRepo bookRepo;

    @Autowired
    BookService bookService;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    Email email;

    List<OrderData> orderDataList = new ArrayList<>();

    @Override
    public Object placeOrder(OrderDto orderDto, String token) {
        int userId = tokenUtil.decodeToken(token);
        if (userRepo.findById(userId).isPresent()) {
            UserData userData = userRepo.findById(userId).get();
            List<CartData> cartDataList = cartRepo.findUsingUserId(userData.getUserId());
            List<BookData> bookDataList = new ArrayList<>();
            int totalBookPrice = 0;
            int totalBookQuantity = 0;
            for (CartData cart : cartDataList) {
                totalBookPrice = (int) (cart.getBookPrice() + totalBookPrice);
                totalBookQuantity = cart.getBookQuantity() + totalBookQuantity;
                bookDataList.add(cart.getBookId());
            }
            OrderData orderData = new OrderData(userData.getUserId(), bookDataList, cartDataList, totalBookQuantity, totalBookPrice, userData.getEmail(), orderDto.getOrderAddress(), false);
            email.sendEmail("hmahur20@gmail.com", "Order placed successfully.. ", "order Details ->" + orderData.getBookData());
            orderRepo.save(orderData);
            orderDataList.add(orderData);
            for (int i = 0; i < cartDataList.size(); i++) {
                BookData bookData1 = cartDataList.get(i).getBookId();
                int updateBookQuantity = bookData1.getBookQuantity() - cartDataList.get(i).getBookQuantity();
                bookData1.setBookQuantity(updateBookQuantity);
                cartRepo.deleteById(cartDataList.get(i).getCartId());
            }
            System.out.println("Order placed");
            return orderData;
        } else {
            throw new BookStoreException("User not found for given usr id");
        }

    }

    @Override
    public List<OrderData> getAll() {
        List<OrderData> orderData = orderRepo.findAll();
        if (orderData.isEmpty()) {
            throw new BookStoreException("No order placed yet");
        } else {
            return orderData;
        }
    }

    @Override
    public List<OrderData> getByUserId(String token) {
        int userId = tokenUtil.decodeToken(token);
        List<OrderData> orderData = orderRepo.findOrderData(userId);
        if (userRepo.findById(userId).isPresent()) {
            if (orderData.isEmpty()) {
                throw new BookStoreException("This Order data is not present");
            } else {
                return orderData;
            }
        } else {
            throw new BookStoreException("This user Id is not present");
        }
    }

    @Override
    public Object getOrderById(int orderId) {
        return orderRepo.findById(orderId).orElseThrow(() -> new BookStoreException("No order placed with this order id =" + orderId));
    }


    @Override
    public String cancelOrder(int orderId) {
        OrderData orderData = (OrderData) this.getOrderById(orderId);
        UserData userData = userRepo.getUserById(orderData.getUserId());
        if (orderData.getCancel() == false) {
            orderRepo.updateCancel(orderId);
            email.sendEmail("hmahur20@gmail.com", "Order cancelled successfully", "Order Data = " + orderData.getBookData());

            List<BookData> bookData = orderData.getBookData();
            for (int j = 0; j < orderDataList.size(); j++) {
                if (orderDataList.get(j).getOrderId() == orderId) {
                    for (int i = 0; i < bookData.size(); i++) {
                        BookData updateBookData = bookService.findById(bookData.get(i).getBookId());
                        for (int k = 0; k < bookData.size(); k++) {
                            int orderedBookQuantity = orderDataList.get(j).getCartId().get(k).getBookQuantity();
                            int orderedBookId = orderDataList.get(j).getCartId().get(k).getBookId().getBookId();
                            int bookId = updateBookData.getBookId();
                            if (orderedBookId == bookId) {
                                int updateQuantity = orderedBookQuantity + updateBookData.getBookQuantity();
                                updateBookData.setBookQuantity(updateQuantity);
                                bookRepo.save(bookData.get(i));
                            }

                        }
                    }
                }
            }
            return "Order cancelled for order id = " + orderId;
        } else {
            throw new BookStoreException("Order is already cancelled");
        }
    }
}

