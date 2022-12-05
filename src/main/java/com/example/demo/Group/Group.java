package com.example.demo.Group;

import com.example.demo.GroupFile.GroupFile;
import com.example.demo.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "groups")
@Table(
        name = "groups"
)

public class Group {
    public Group(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGroupUsers(List<User> groupUsers) {
        this.groupUsers = groupUsers;
    }

    @Id
    @SequenceGenerator(
            name = "sequence_groups",
            sequenceName = "sequence_groups",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "sequence_groups"
    )
    @Column(name = "id", updatable = false)
    private Long id;

//    public List<GroupFile> getFileList() {
//        return fileList;
//    }
//
//    public void setFileList(List<GroupFile> fileList) {
//        this.fileList = fileList;
//    }

    @Column(name = "name", nullable = false, updatable = false)
    private String name;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.id")
    private User user;

    @ManyToMany()
    @JoinTable(
            name="group_user",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public List<User> groupUsers=new ArrayList<>();
//    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, orphanRemoval = true)
//    public List<GroupFile> fileList= new ArrayList<>();
    public List<User> getGroupUsers() {
        return groupUsers;
    }

    public Group(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
