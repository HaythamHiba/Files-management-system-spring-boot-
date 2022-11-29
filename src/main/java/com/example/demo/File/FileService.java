package com.example.demo.File;

import com.example.demo.Group.Group;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class FileService {

    private final FileRepository fileRepository;
    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> getAll(Long id) {
        Group group = new Group();
        group.setId(id);
           return fileRepository.findFilesByGroup(group);

    }

    public void importFileToGroup(FileDTO fileDto) {

        try {

        String encodedString = Base64.getEncoder().encodeToString(fileDto.getFile().getBytes());
        Group group=new Group();
        group.setId(fileDto.getGroup_id());
            File file=new File(fileDto.getName(),encodedString,null,group,null);

            this.fileRepository.save(file);
        }catch (Exception ignore){

        }



    }

    public void deleteFile(Long id) {
        this.fileRepository.deleteById(id);
    }
}
