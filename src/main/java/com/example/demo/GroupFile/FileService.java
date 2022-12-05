package com.example.demo.GroupFile;

import com.example.demo.Group.Group;

import com.example.demo.Group.GroupRepositroy;
import com.example.demo.Response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final GroupRepositroy groupRepositroy;

    @Autowired
    public FileService(FileRepository fileRepository, GroupRepositroy groupRepositroy) {
        this.fileRepository = fileRepository;
        this.groupRepositroy = groupRepositroy;
    }


    private final String path = "C:\\Users\\UsEr\\Desktop\\spring\\src\\static";


    public ResponseEntity<Map<String, Object>> getAll(Long id) {

        Optional<Group> found = groupRepositroy.findById(id);

        try {
            if (found.isPresent()) {

                return ResponseHandler.responseBuilder("ok", HttpStatus.OK, fileRepository.findGroupFilesByGroupId(id));

            } else throw new IllegalStateException("No Such User");
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);

        }

    }

    public ResponseEntity<Map<String, Object>> importFileToGroup(MultipartFile fileToImport, Long id) throws IOException {

        String filePath = path + fileToImport.getOriginalFilename();


        Optional<Group> found = groupRepositroy.findById(id);

        try {
            if (found.isPresent()) {

                fileRepository.save(new GroupFile(fileToImport.getOriginalFilename(), fileToImport.getContentType(), filePath, id));

                fileToImport.transferTo(new File(filePath));

            } else {
                throw new IllegalStateException("No Such User");
            }
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);
        }
        return ResponseHandler.responseBuilder("OK", HttpStatus.OK, null);
    }

    public ResponseEntity<Map<String, Object>> deleteFile(Long id) {

        Optional<GroupFile> found = this.fileRepository.findById(id);
        try {
            if (found.isPresent()) {

                String path = found.get().getPath();


                File file = new File(path);
                if (file.delete())
                    this.fileRepository.deleteById(id);
            } else throw new IllegalStateException("Error in Deleteing File");
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);
        }
        return ResponseHandler.responseBuilder("DELETED", HttpStatus.OK, null);
    }
}
