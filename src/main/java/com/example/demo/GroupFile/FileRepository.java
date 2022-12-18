package com.example.demo.GroupFile;

import com.example.demo.Group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<GroupFile, Long> {

    List<GroupFile> findGroupFilesByGroupId(Long id);

    List<GroupFile> findAllByCheckUserIdAndGroupId(Long user_id, Long group_id);

    List<GroupFile> findAllByGroupIdAndCheckUserIdIsNotNull(Long group_id);
}
