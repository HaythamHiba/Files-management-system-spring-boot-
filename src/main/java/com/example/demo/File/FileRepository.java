package com.example.demo.File;

import com.example.demo.Group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {

    List<File> findFilesByGroup(Group group);

}
