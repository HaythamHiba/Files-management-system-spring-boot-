package com.example.demo.security;

import com.example.demo.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    Optional <User> findUserByUsername(String username);


}
