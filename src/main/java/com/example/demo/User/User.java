package com.example.demo.User;

import com.example.demo.Group.Group;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")

@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "users_username_unique", columnNames = "username")
        }
)
public class User {
    public User(){}
    @Id
    @SequenceGenerator(
            name = "sequence_users",
            sequenceName = "sequence_users",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "sequence_users"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "username", updatable = false, nullable = false)
    private String username;
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Group> groupList = new ArrayList<>();


    @ManyToMany(
            mappedBy = "groupUsers"
    )
    private List<Group> userGroups=new ArrayList<>();

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public void setUserGroups(List<Group> userGroups) {
        this.userGroups = userGroups;
    }

    public List<Group> getUserGroups() {
        return userGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
}
