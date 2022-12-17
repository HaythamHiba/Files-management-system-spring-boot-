package com.example.demo.log;

import com.example.demo.Response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LogMethodsService {

    public final LogMethodsRepo logMethodsRepo;

    @Autowired
    public LogMethodsService(LogMethodsRepo logMethodsRepo) {
        this.logMethodsRepo = logMethodsRepo;
    }


    public ResponseEntity<Map<String, Object>> getAll() {
        return ResponseHandler.responseBuilder("Ok", HttpStatus.OK,logMethodsRepo.findAll());
    }

}
