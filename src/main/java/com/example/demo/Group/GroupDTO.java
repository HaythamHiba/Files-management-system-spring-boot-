package com.example.demo.Group;

import javax.validation.constraints.NotEmpty;

public class GroupDTO {
    @NotEmpty
    private String name;

    private Long user_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public GroupDTO() {
    }

    public GroupDTO(@NotEmpty String name, @NotEmpty Long user_id) {
        this.name = name;
        this.user_id = user_id;
    }
}
