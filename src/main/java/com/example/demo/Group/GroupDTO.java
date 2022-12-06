package com.example.demo.Group;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GroupDTO {
    @NotEmpty

    private String name;

    @NotNull(message = "user_id is required")

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
