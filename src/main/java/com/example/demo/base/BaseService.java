package com.example.demo.base;

import com.example.demo.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseService {

    public UserDetailsImpl getUser(){

        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
