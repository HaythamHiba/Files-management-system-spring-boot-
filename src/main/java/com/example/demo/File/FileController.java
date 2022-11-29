package com.example.demo.File;

import com.example.demo.Group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FileController {
    private final FileService fileService;
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(path = "files/{id}")
    public List<File> getAll(@PathVariable("id") Long id) {
        return this.fileService.getAll(id);
    }

    @PostMapping(path = "files")
    public void importFileToGroup(@Valid @RequestBody FileDTO fileDto ){
         this.fileService.importFileToGroup(fileDto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteFile(@PathVariable("id")Long id){
        this.fileService.deleteFile(id);
    }

}
