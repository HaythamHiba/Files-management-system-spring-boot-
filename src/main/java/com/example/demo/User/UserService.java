package com.example.demo.User;

import com.example.demo.Group.Group;
import com.example.demo.Group.GroupRepositroy;
import com.example.demo.Response.ResponseHandler;
import com.example.demo.base.BaseService;
import com.example.demo.security.JwtUtils;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.security.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService extends BaseService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;
    public final PasswordEncoder passwordConfig;
    @Autowired
    public GroupRepositroy groupRepositroy;


    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils, PasswordEncoder passwordConfig) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
        this.passwordConfig = passwordConfig;
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

    public ResponseEntity<Map<String, Object>> loginUser(UserLoginDTO userLoginDTO) {


        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDTO
                                    .getUsername(),
                            userLoginDTO
                                    .getPassword()
                    )
            );

            Optional<User> user = userRepository.getUserByUsername(userLoginDTO.username);
            if (user.isPresent()) {
                UserDetailsImpl userDetails = new UserDetailsImpl(user.get());
                String token = jwtUtils.generateToken(userDetails);
                return ResponseHandler.responseBuilder("Login Successfully", HttpStatus.OK, new ResponseDto(user.get(), token));
            } else {
                throw new IllegalStateException("Username or password doesn't match");

            }

        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Username or password doesn't match", HttpStatus.FORBIDDEN, null);

        }


    }

    public ResponseEntity<Map<String, Object>> registerUser(UserRegisterDto userRegisterDto) {

        UserDetailsImpl user = userDetailsService.loadUserByUsername(userRegisterDto.getUsername());
        try {
            if (user != null) {
                throw new IllegalStateException("Error ");

            }
            User newUser = new User(userRegisterDto.getUsername(), passwordConfig.encode(userRegisterDto.getPassword()), userRegisterDto.getName());
            UserDetailsImpl user2 = new UserDetailsImpl(newUser);
            String token = jwtUtils.generateToken(user2);
            userRepository.save(newUser);
            return ResponseHandler.responseBuilder("Register Successfully", HttpStatus.OK, new ResponseDto(newUser, token));


        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);

        }

    }

    public ResponseEntity<Map<String, Object>> getAllUserGroups() {
        User user = getUser().getUser();
        List<Long> userGroupsIds = this.groupRepositroy.getAllUserGroups(user.getId());
        List<Group> returnArray = new ArrayList<>();
        for (int i = 0; i < userGroupsIds.size(); i++) {
            returnArray.add(this.groupRepositroy.findById(userGroupsIds.get(i)).get());
        }
       return ResponseHandler.responseBuilder("OK", HttpStatus.OK, returnArray);
    }


}

@Data
@AllArgsConstructor
class ResponseDto {
    public User user;
    public String token;

}