package com.example.demo.Group;

import com.example.demo.User.User;
import com.example.demo.base.BaseService;
import com.example.demo.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(path = "api/groups")
public class GroupController  {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "getAll")
    public ResponseEntity<Map<String, Object>> getAll() {
        return this.groupService.getAll();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable("id") Long id) {
        return this.groupService.getById(id);
    }

    @PostMapping(path = "import")
    public ResponseEntity<Map<String, Object>> importGroup(@Valid @RequestBody GroupDTO groupDTO) {


       return this.groupService.importGroup(groupDTO);
    }


    @PostMapping (path = "{group_id}")
    public ResponseEntity<Map<String, Object>> addUserToGroup(
            @PathVariable("group_id") Long group_id

    ) {
         return this.groupService.addUserToGroup(group_id);
    }
}
