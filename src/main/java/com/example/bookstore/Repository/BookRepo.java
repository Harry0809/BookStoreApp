package com.example.bookstore.Repository;

import com.example.bookstore.Model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookData, Integer> {
    @Query(value = "SELECT * FROM bookStore.book_data order by book_name;", nativeQuery = true)
    List<BookData> sortByBookName();

    @Query(value = "SELECT * FROM bookStore.book_data order by book_price ASC;", nativeQuery = true)
    List<BookData> sortByPriceAscending();

    @Query(value = "SELECT * FROM bookStore.book_data order by book_price DESC;", nativeQuery = true)
    List<BookData> sortByPriceDescending();

    @Modifying
    @Transactional
    @Query(value = "update bookStore.book_data set book_quantity= :bookQuantity where book_id= :bookId", nativeQuery = true)
    void updateBookQuantity(int bookId, int bookQuantity);

}
