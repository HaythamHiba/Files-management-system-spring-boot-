package com.example.demo.Group;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    @NotEmpty(message = "Group name is required")

    private String name;
    @NotEmpty(message = "Group type is required")
    private String groupType;
    private Long maxNumber;




}
