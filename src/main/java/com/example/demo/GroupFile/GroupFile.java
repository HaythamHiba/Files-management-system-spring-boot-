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
    private String fileStatus;

    public GroupFile(String name, String type, String path, Long groupId, String fileStatus) {
        this.name = name;
        this.type = type;
        this.path = path;
        this.groupId = groupId;
        this.fileStatus = fileStatus;
    }

    public GroupFile() {
    }

    public GroupFile(Long id, String name, String type, String path) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.path = path;
    }

    @Override
    public String toString() {
        return "GroupFile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", path='" + path + '\'' +
                ", groupId=" + groupId +
                ", fileStatus='" + fileStatus + '\'' +
                '}';
    }
}
