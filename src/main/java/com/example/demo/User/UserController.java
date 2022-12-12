package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(path = "api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "user/getAll")
    public ResponseEntity<Map<String, Object>> getAll() {
        return this.userService.getAll();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> importUser(@RequestBody User user) {
        return this.userService.importUser(user);
    }



    @PostMapping(path = "auth/login")
    public ResponseEntity<Map<String,Object>>login(@Valid @RequestBody UserLoginDTO userLoginDTO){

        return this.userService.loginUser(userLoginDTO);
    }

    @PostMapping(path = "auth/register")
    public ResponseEntity<Map<String,Object>>register(@Valid @RequestBody UserRegisterDto userRegisterDto){

        return this.userService.registerUser(userRegisterDto);
    }


}
