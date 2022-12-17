package com.example.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> getUserByUsername(String username);

     @Query(value = "SELECT t.user_id FROM group_user t WHERE t.group_id = ?1", nativeQuery = true)
     List<Long> getAllByGroupId(Long group_id);
}
