package com.example.demo.Report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query()
    List<Report> getAllReportsByUserId(Long user_id);

    @Query
    List<Report> getAllReportsByFileId(Long file_id);
}
