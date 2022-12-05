package com.example.demo.Group;

import com.example.demo.Response.ResponseHandler;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GroupService {
    private final GroupRepositroy groupRepositroy;
    private final UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepositroy groupRepositroy, UserRepository userRepository) {
        this.groupRepositroy = groupRepositroy;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Map<String, Object>> getAll() {
        return ResponseHandler.responseBuilder("OK", HttpStatus.OK, this.groupRepositroy.findAll());
    }

    public ResponseEntity<Map<String, Object>> getById(Long id) {
        return ResponseHandler.responseBuilder("ok", HttpStatus.OK, this.groupRepositroy.findById(id));
    }

    public ResponseEntity<Map<String, Object>> importGroup(Group group) {
        return ResponseHandler.responseBuilder("Ok", HttpStatus.OK, this.groupRepositroy.save(group));
    }

    public ResponseEntity<Map<String, Object>> addUserToGroup(Long group_id, Long user_id) {
        try {
            User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalStateException("No Such User"));
            Group group = groupRepositroy.findById(group_id).orElseThrow(() -> new IllegalStateException("No Such Group"));


            if (!group.groupUsers.contains(user)) {

                group.groupUsers.add(user);
            } else throw new IllegalStateException("user is already in group");


            this.groupRepositroy.save(group);
            return ResponseHandler.responseBuilder("ok", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);
        }
    }
}
