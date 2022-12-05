package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "getAll")
    public ResponseEntity<Map<String, Object>> getAll() {
        return this.userService.getAll();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> importUser(@RequestBody User user) {
        return this.userService.importUser(user);
    }


}
