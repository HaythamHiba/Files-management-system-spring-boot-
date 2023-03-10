package com.example.demo.GroupFile;

import com.example.demo.Response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api")
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

    @PostMapping(path = "files/group/check")
    public ResponseEntity<Map<String, Object>> checkFile(@RequestBody  Map<String,List<Long>> body) throws IOException {
        try {

            return this.fileService.checkFiles(body.get("files"));
        }catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);
        }
    }

    @PostMapping(path = "files/group/free")
    public ResponseEntity<Map<String, Object>> uncheckFile(@RequestBody  Map<String,List<Long>> body) throws IOException {
        try{

            return this.fileService.uncheckFile(body.get("files"));
        }catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);

        }
    }

    @DeleteMapping(path = "files/{id}")
    public ResponseEntity<Map<String, Object>> deleteFile(@PathVariable("id") Long id) {
        return this.fileService.deleteFile(id);
    }

}
