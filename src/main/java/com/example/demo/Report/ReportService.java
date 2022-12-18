package com.example.demo.Report;

import com.example.demo.GroupFile.FileRepository;
import com.example.demo.Response.ResponseHandler;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, UserRepository userRepository, FileRepository fileRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
        this.fileRepository = fileRepository;
    }

    public ResponseEntity<Map<String, Object>> getAllUserReports(Long user_id) {
        return ResponseHandler.responseBuilder("OK", HttpStatus.OK, this.reportRepository.getAllByUser(userRepository.findById(user_id).get()));
    }

    public ResponseEntity<Map<String, Object>> getAllFileReports(Long file_id) {
        return ResponseHandler.responseBuilder("OK", HttpStatus.OK, this.reportRepository.getAllByGroupFile(fileRepository.findById(file_id).get()));
    }
}
