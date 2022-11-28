package com.example.demo.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/groups")
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "getAll")
    public List<Group> getAll() {
        return this.groupService.getAll();
    }

    @GetMapping(path = "{id}")
    public Optional<Group> getById(@PathVariable("id") Long id) {
        return this.groupService.getById(id);
    }

    @PostMapping(path = "import")
    public void importGroup(@RequestBody Group group) {
        Group group1=new Group(group.getName(),group.getUser());
        this.groupService.importGroup(group1);
    }


    @PostMapping (path = "{group_id}/user/{user_id}")
    public void addUserToGroup(
            @PathVariable("group_id") Long group_id,
            @PathVariable("user_id") Long user_id
    ) {
         this.groupService.addUserToGroup(group_id,user_id);
    }
}
