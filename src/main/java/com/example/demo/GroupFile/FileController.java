package com.example.demo.GroupFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class FileController {


    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(path = "files/group/{id}")
    public ResponseEntity<Map<String, Object>> getAll(@PathVariable("id") Long id) {
        return this.fileService.getAll(id);
    }

    @PostMapping(path = "files/group/{id}")
    public ResponseEntity<Map<String, Object>> importFileToGroup(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) throws IOException {
        return this.fileService.importFileToGroup(file, id);
    }

    @PostMapping(path = "files/group/check/{id}")
    public ResponseEntity<Map<String, Object>> checkFile(@PathVariable("id") Long id) throws IOException {
        return this.fileService.checkFile(id);
    }

    @PostMapping(path = "files/group/free/{id}")
    public ResponseEntity<Map<String, Object>> uncheckFile(@PathVariable("id") Long id) throws IOException {
        return this.fileService.uncheckFile(id);
    }

    @DeleteMapping(path = "files/{id}")
    public ResponseEntity<Map<String, Object>> deleteFile(@PathVariable("id") Long id) {
        return this.fileService.deleteFile(id);
    }

}
