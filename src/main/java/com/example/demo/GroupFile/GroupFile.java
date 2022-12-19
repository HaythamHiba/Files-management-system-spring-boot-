package com.example.demo.GroupFile;


import com.example.demo.Report.Report;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.demo.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name="group_file")
@Entity(name = "group_file")
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


    public Long getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(Long checkUserId) {
        this.checkUserId = checkUserId;
    }

    private String path;
    private Long checkUserId;
    @Column(nullable = false)
    private Long groupId;
    private String fileStatus;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    public User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GroupFile(String name, String type, String path, Long groupId, String fileStatus

    ) {
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
