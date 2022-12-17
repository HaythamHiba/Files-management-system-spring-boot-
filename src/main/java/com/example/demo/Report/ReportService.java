package com.example.demo.Report;

import com.example.demo.Response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public ResponseEntity<Map<String, Object>> getAllUserReports(Long user_id) {
        return ResponseHandler.responseBuilder("OK", HttpStatus.OK, this.reportRepository.getAllReportsByUserId(user_id));
    }

    public ResponseEntity<Map<String, Object>> getAllFileReports(Long file_id) {
        return ResponseHandler.responseBuilder("OK", HttpStatus.OK, this.reportRepository.getAllReportsByFileId(file_id));
    }
}
