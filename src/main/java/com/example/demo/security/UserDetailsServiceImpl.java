package com.example.demo.security;

import com.example.demo.User.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user=userDao.findUserByUsername(username);
        UserDetailsImpl userDetails ;
        try {
            if(!user.isPresent()){
                throw new UsernameNotFoundException("User Not Found");

            }

             userDetails=new UserDetailsImpl(user.get());


        }catch (Exception ignored) {
            return null;


        }

        return userDetails;


    }
}
