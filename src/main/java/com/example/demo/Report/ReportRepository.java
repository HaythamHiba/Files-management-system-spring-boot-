package com.example.demo.Report;

import com.example.demo.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {


    List<Report> getAllByUser(User user);


}

