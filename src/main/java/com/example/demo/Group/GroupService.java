package com.example.demo.Group;

import com.example.demo.GroupFile.FileRepository;
import com.example.demo.GroupFile.GroupFile;
import com.example.demo.Response.ResponseHandler;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import com.example.demo.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class GroupService extends BaseService {
    private final GroupRepositroy groupRepositroy;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    @Autowired
    public GroupService(GroupRepositroy groupRepositroy, UserRepository userRepository, FileRepository fileRepository) {
        this.groupRepositroy = groupRepositroy;
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }


    public ResponseEntity<Map<String, Object>> getAll() {
        return ResponseHandler.responseBuilder("OK", HttpStatus.OK, this.groupRepositroy.findAll());
    }

    public ResponseEntity<Map<String, Object>> getById(Long id) {
        return ResponseHandler.responseBuilder("ok", HttpStatus.OK, this.groupRepositroy.findById(id));
    }

    public ResponseEntity<Map<String, Object>> importGroup(GroupDTO groupDTO) {

        try {
            User user = getUser().getUser();
            if(groupDTO.getGroupType().equals(GroupType.Private.toString())
                &&(groupDTO.getMaxNumber() < 0 || groupDTO.getMaxNumber() == 0)
            ){
                throw new IllegalStateException("Group max number should be not null and more than 0");
            }


            Group group = new Group(
                    groupDTO.getGroupType(),
                    groupDTO.getMaxNumber(),
                    groupDTO.getName(),
                    user
            );


            Optional<Group> found = groupRepositroy.findGroupByName(group.getName());

            if (found.isPresent()) {
                throw new IllegalStateException("name is already taken");
            }
            group.groupUsers.add(user);
            this.groupRepositroy.save(group);

            return ResponseHandler.responseBuilder("Ok", HttpStatus.OK, group);


        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);

        }

    }

    public ResponseEntity<Map<String, Object>> addUserToGroup(Long group_id) {


        try {
            User user = getUser().getUser();


            Group group = groupRepositroy.findById(group_id).orElseThrow(() -> new IllegalStateException("No Such Group"));


            List<Long> groupUsersIds = this.userRepository.getAllByGroupId(group_id);


            if (
                    !groupUsersIds.contains(user.getId())
                    ) {
                if(group.getGroupType().equals(GroupType.Private.toString())&&
                        group.getGroupUsers().size()<group.getMaxNumber()
                ){
                    group.groupUsers.add(user);
                    this.groupRepositroy.save(group);

                }else throw new IllegalStateException("Group is full");
            } else throw new IllegalStateException("user is already in group");


            return ResponseHandler.responseBuilder("ok", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);
        }
    }

    public ResponseEntity<Map<String, Object>> getGroupUsers(Long group_id) {
        try {
            List<Long> groupUsersIds = this.userRepository.getAllByGroupId(group_id);
            List<User> returnArray = new ArrayList<>();
            for (Long groupUsersId : groupUsersIds) {
                returnArray.add(this.userRepository.findById(groupUsersId).get());
            }
            return ResponseHandler.responseBuilder("OK", HttpStatus.OK, returnArray);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteUserFromGroup(Long group_id, Long user_id) {
        try {
            Optional<Group> group = this.groupRepositroy.findById(group_id);
            Optional<User> user = this.userRepository.findById(user_id);

            if (!group.isPresent()) {
                throw new IllegalStateException("group does not exist");
            }
            if (!getUser().getUser().getId().equals(group.get().getUser().getId())) {
                throw new IllegalStateException("unauthorized");
            }
            if (!user.isPresent()) {
                throw new IllegalStateException("user does not exist");
            }
            if (!group.get().getGroupUsers().contains(user.get())) {
                throw new IllegalStateException("user is not in group");
            }
            List<GroupFile> userCheckedFiles = this.fileRepository.findAllByCheckUserIdAndGroupId(user.get().getId(), group.get().getId());
            if (userCheckedFiles.size() != 0) {
                throw new IllegalStateException("user has some checked in files");
            }
            group.get().getGroupUsers().remove(user.get());
            return ResponseHandler.responseBuilder("OK", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);
        }
    }

    ResponseEntity<Map<String, Object>> deleteGroup(Long group_id) {
        try {
            Optional<Group> group = this.groupRepositroy.findById(group_id);
            if (!group.isPresent()) {
                throw new IllegalStateException("group does not exist");
            }
            if (!getUser().getUser().getId().equals(group.get().getUser().getId())) {
                throw new IllegalStateException("unauthorized");
            }
            List<GroupFile> groupFiles = this.fileRepository.findAllByGroupIdAndCheckUserIdIsNotNull(group_id);
            if (groupFiles.size() != 0) {
                throw new IllegalStateException("group has some checked in files");
            }
            this.groupRepositroy.delete(group.get());
            return ResponseHandler.responseBuilder("OK", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);
        }
    }

    public ResponseEntity<Map<String, Object>> addUserToGroupByAdmin(Long group_id, Long user_id) {
        try {
            Optional<Group> group = this.groupRepositroy.findById(group_id);
            Optional<User> user = this.userRepository.findById(user_id);
            if (!group.isPresent()) {
                throw new IllegalStateException("group does not exist");
            }
            if (!user.isPresent()) {
                throw new IllegalStateException("user does not exist");
            }
            if (group.get().getGroupUsers().contains(user.get())) {
                throw new IllegalStateException("user already in group");
            }
            if (!getUser().getUser().getId().equals(group.get().getUser().getId())) {
                throw new IllegalStateException("unauthorized");
            }
            group.get().getGroupUsers().add(user.get());
            return ResponseHandler.responseBuilder("OK", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);
        }
    }
}
