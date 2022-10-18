package com.example.bookstore.Repository;

import com.example.bookstore.Model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserData, Integer> {
    @Query(value = "SELECT * FROM bookStore.user_data order by user_name;", nativeQuery = true)
    List<UserData> sortByUserName();

    @Query(value = "SELECT * FROM bookStore.user_data WHERE user_id= :userId", nativeQuery = true)
    UserData getUserById(Integer userId);
}
