package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "getAll")
    public List<User> getAll() {
        return this.userService.getAll();
    }

    @PostMapping
    public void importUser(@RequestBody User user) {
        this.userService.importUser(user);
    }


}
