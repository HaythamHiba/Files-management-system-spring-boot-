package com.example.demo.Group;

import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepositroy groupRepositroy;
    private final UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepositroy groupRepositroy,UserRepository userRepository) {
        this.groupRepositroy = groupRepositroy;
        this.userRepository=userRepository;
    }

    public List<Group> getAll() {
        return this.groupRepositroy.findAll();
    }

    public Optional<Group> getById(Long id) {
        return this.groupRepositroy.findById(id);
    }

    public void importGroup(Group group) {
        this.groupRepositroy.save(group);
    }

    public void addUserToGroup(Long group_id, Long user_id) {
        User user=userRepository.findById(user_id).orElseThrow(()->new IllegalStateException("No Such User"));
        Group group=groupRepositroy.findById(group_id).orElseThrow(()->new IllegalStateException("No Such Group"));
        group.groupUsers.add(user);
                this.groupRepositroy.save(group);

    }
}
