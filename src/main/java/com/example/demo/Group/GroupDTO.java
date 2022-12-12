package com.example.demo.Group;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GroupDTO {
    @NotEmpty

    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public GroupDTO() {
    }

    public GroupDTO(@NotEmpty String name) {
        this.name = name;

    }
}
