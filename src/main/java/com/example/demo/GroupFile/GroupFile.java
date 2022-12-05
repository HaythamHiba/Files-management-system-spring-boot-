package com.example.demo.GroupFile;


import lombok.Data;

import javax.persistence.*;

@Data

@Entity(name = "group_file")
@Table
public class GroupFile {


    @Id
    @SequenceGenerator(
            name = "sequence_group_file",
            sequenceName = "sequence_group_file",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "sequence_group_file"
    )
    private Long id;
    private String name;
    private String type;
    private String path;
    @Column(nullable = false)
    private Long groupId;

    public GroupFile(String name, String type, String path,Long groupId) {
        this.name = name;
        this.type = type;
        this.path = path;
        this.groupId=groupId;
    }

    public GroupFile() {
    }

    public GroupFile(Long id, String name, String type, String path) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.path = path;
    }
}
