package com.example.demo.Group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepositroy extends JpaRepository<Group, Long> {
    Optional<Group> findGroupByName(String name);

    @Query(value = "SELECT t.group_id FROM group_user t WHERE t.user_id= ?1", nativeQuery = true)
    List<Long> getAllUserGroups(Long user_id);
}
