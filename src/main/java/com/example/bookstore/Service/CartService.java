package com.example.bookstore.Service;

import com.example.bookstore.Dto.CartDto;
import com.example.bookstore.ExceptionHandling.BookStoreException;
import com.example.bookstore.Model.BookData;
import com.example.bookstore.Model.CartData;
import com.example.bookstore.Model.UserData;
import com.example.bookstore.Repository.BookRepo;
import com.example.bookstore.Repository.CartRepo;
import com.example.bookstore.Repository.UserRepo;
import com.example.bookstore.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartInterface {

    @Autowired
    CartRepo cartRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    TokenUtil tokenUtil;
    @Override
    public Object addInCart(CartDto cartDto, String token) {
        int userId = tokenUtil.decodeToken(token);
        Optional<UserData> userData = userRepo.findById(userId);
        Optional<BookData> bookData = bookRepo.findById(cartDto.getBookId());
        if (bookData.isPresent() && userData.isPresent() && bookData.get().getBookQuantity() >= cartDto.getBookQuantity()) {
            List<CartData> cartDataList = cartRepo.findAll();
            for (CartData data : cartDataList) {
                if (data.getCartId().equals(cartDto.getBookId())) {
                    throw (new BookStoreException("Cart is already existing"));
                }
            }
            double bookPrice = bookData.get().getBookPrice() * cartDto.getBookQuantity();
            CartData cartData = new CartData(userData.get(), bookData.get(), cartDto.getBookQuantity(), bookPrice);
            return cartRepo.save(cartData);
        } else {
            throw new BookStoreException("Book or user data is not available to add in cart");
        }
    }

    @Override
    public List<CartData> getCartData(String token) {
        int userId = tokenUtil.decodeToken(token);
        List<CartData> cartData = cartRepo.findCartData(userId);
        if (userRepo.findById(userId).isPresent()) {
            if (cartData.isEmpty()) {
                throw new BookStoreException("This user cart details ar not available in database");
            } else {
                return cartData;
            }
        } else {
            throw new BookStoreException("This user id is not present");
        }
    }

    @Override
    public Object dataById(int cartId, String token) {
        return cartRepo.findById(cartId).orElseThrow(() -> new BookStoreException("Employee with this cart id=" + " " + cartId + " " + " is not available in the database"));
    }


    @Override
    public String removeItemFromCart(int cartId, String token) {
        int userId = tokenUtil.decodeToken(token);
        Optional<CartData> cartData = cartRepo.findById(cartId);
        if (cartData.isPresent() && userId == cartData.get().getUserId().getUserId()) {
            cartRepo.deleteById(cartId);
            return "Cart data deleted successfully";
        } else {
            throw new BookStoreException("This Id is not available");
        }
    }

    @Override
    public Object updateCart(String token, int bookQuantity, int cartId) {
        int userId = tokenUtil.decodeToken(token);
        Optional<CartData> cartData = cartRepo.findById(userId);
        if (cartData.isPresent()) {
            Optional<BookData> bookData = bookRepo.findById(cartData.get().getBookId().getBookId());
            if (bookData.get().getBookQuantity() >= bookQuantity) {
                cartData.get().setBookQuantity(bookQuantity);
                cartRepo.save(cartData.get());
                bookRepo.save(bookData.get());
                return cartData.get();
            } else {
                throw new BookStoreException("Book data is not present");
            }
        } else {
            throw new BookStoreException("cart data is not present");
        }
    }

    @Override
    public Object updateCartByToken(CartDto cartDto, String token, int cartId) {
        int userId = tokenUtil.decodeToken(token);
        Optional<CartData> cartData = cartRepo.findById(cartId);
        Optional<BookData> bookData = bookRepo.findById(cartDto.getBookId());
        Optional<UserData> userData = userRepo.findById(userId);
        if (cartData.isEmpty()) {
            throw new BookStoreException("Cart does not exist");
        } else {
            if (bookData.isPresent() && userData.isPresent()) {
                double bookPrice = bookData.get().getBookPrice() * cartDto.getBookQuantity();
                CartData cartData1 = new CartData(userData.get(), bookData.get(), cartDto.getBookQuantity(), bookPrice);
                return cartRepo.save(cartData1);
            } else {
                throw new BookStoreException("Book data or user token is not available in database");
            }
        }
    }
}