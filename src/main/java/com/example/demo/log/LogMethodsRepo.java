package com.example.demo.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMethodsRepo extends JpaRepository<LogMethods,Long> {
}
