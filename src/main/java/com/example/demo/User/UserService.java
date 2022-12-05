package com.example.demo.User;

import com.example.demo.Response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Map<String, Object>> getAll() {
        return ResponseHandler.responseBuilder("ok", HttpStatus.OK, this.userRepository.findAll());
    }

    public ResponseEntity<Map<String, Object>> importUser(User user) {
        Optional<User> user1 = this.userRepository.getUserByUsername(user.getUsername());
        try {
            if (user1.isPresent()) {
                throw new IllegalStateException("Username already taken");
            }
            return ResponseHandler.responseBuilder("Created", HttpStatus.CREATED, this.userRepository.save(user));
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);

        }
    }
}
