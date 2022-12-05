package com.example.demo.GroupFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class FileController {


        private final FileService fileService;
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
        @GetMapping(path = "files/group/{id}")
    public List<GroupFile> getAll(@PathVariable("id") Long id) {
        return this.fileService.getAll(id);
    }

    @PostMapping(path = "files/group/{id}")
    public void importFileToGroup(@RequestParam("file") MultipartFile file,@PathVariable("id")Long id) throws IOException {
         this.fileService.importFileToGroup(file,id);
    }

    @DeleteMapping(path = "files/{id}")
    public void deleteFile(@PathVariable("id")Long id){
        this.fileService.deleteFile(id);
    }

}
