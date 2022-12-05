package com.example.demo.Group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepositroy extends JpaRepository<Group,Long> {


}
