package com.example.demo.Group;

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

    private String groupType;
    private Long maxNumber;

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Long getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Long maxNumber) {
        this.maxNumber = maxNumber;
    }

    @Column(name = "name",unique = true, nullable = false, updatable = false)

    private String name;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.id")
    private User user;

    @JsonIgnore
    @ManyToMany()
    @JoinTable(
            name="group_user",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public List<User> groupUsers=new ArrayList<>();

    public List<User> getGroupUsers() {
        return groupUsers;
    }

    public Group(String groupType, Long maxNumber, String name, User user) {
        this.groupType = groupType;
        this.maxNumber = maxNumber;
        this.name = name;
        this.user = user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name+ '\'' +
                ", groupType='" + groupType+ '\'' +
                ", maxNumber='" + maxNumber+ '\'' +

                '}';
    }
}
