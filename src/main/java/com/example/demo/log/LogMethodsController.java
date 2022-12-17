package com.example.demo.log;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "api/log")
public class LogMethodsController {

    public final LogMethodsService logMethodsService;

    public LogMethodsController(LogMethodsService logMethodsService) {
        this.logMethodsService = logMethodsService;
    }


    @GetMapping
       public ResponseEntity<Map<String, Object>> getAll(){
       return logMethodsService.getAll();
    }
}
