package com.example.demo.Group;

import com.example.demo.Response.ResponseHandler;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import com.example.demo.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class GroupService extends BaseService {
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

    public ResponseEntity<Map<String, Object>> importGroup(GroupDTO groupDTO) {
        User user=getUser().getUser();


        Group group=new Group(groupDTO.getName(),user);
        try {

            Optional<Group> found=groupRepositroy.findGroupByName(group.getName());
            if (found.isPresent()){
                throw new IllegalStateException("name is already taken");
            }
            return ResponseHandler.responseBuilder("Ok", HttpStatus.OK, this.groupRepositroy.save(group));


        }catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);

        }

    }

    public ResponseEntity<Map<String, Object>> addUserToGroup(Long group_id) {




        try {
            User user=getUser().getUser();


            Group group = groupRepositroy.findById(group_id).orElseThrow(() -> new IllegalStateException("No Such Group"));


            if (!group.getGroupUsers().contains(user)) {

                group.groupUsers.add(user);
                this.groupRepositroy.save(group);
            } else throw new IllegalStateException("user is already in group");



            return ResponseHandler.responseBuilder("ok", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);
        }
    }
}
