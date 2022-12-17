package com.example.demo.Report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query(value = "SELECT r FROM Report r WHERE r.user_id= ?1", nativeQuery = true)
    List<Report> getAllReportsByUserId(Long user_id);

    @Query(value = "SELECT r FROM Report r WHERE r.group_file_id= ?1", nativeQuery = true)
    List<Report> getAllReportsByFileId(Long file_id);
}
