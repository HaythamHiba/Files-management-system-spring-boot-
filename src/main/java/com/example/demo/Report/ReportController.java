package com.example.demo.Report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(path = "user/{user_id}")
    public ResponseEntity<Map<String, Object>> getAllUserReports(@PathVariable("user_id") Long user_id) {
        return this.reportService.getAllUserReports(user_id);
    }

    @GetMapping(path = "file/{file_id}")
    public ResponseEntity<Map<String, Object>> getAllFileReports(@PathVariable("file_id") Long file_id) {
        return this.reportService.getAllFileReports(file_id);
    }
}
