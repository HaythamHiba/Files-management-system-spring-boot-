package com.example.demo.GroupFile;

import com.example.demo.Group.Group;

import com.example.demo.Group.GroupRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
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




    private final String path="C:\\Users\\UsEr\\Desktop\\spring\\src\\static";


    public List<GroupFile> getAll(Long id) {

        Optional<Group> found= groupRepositroy.findById(id);

        if(found.isPresent()) {

            return fileRepository.findGroupFilesByGroupId(id);

        }else throw new IllegalStateException("No Such User");


    }

    public void importFileToGroup(MultipartFile fileToImport,Long id) throws IOException {

        String filePath=path+fileToImport.getOriginalFilename();



       Optional<Group> found= groupRepositroy.findById(id);

        if(found.isPresent()){

            fileRepository.save(
                    new GroupFile(fileToImport.getOriginalFilename(),fileToImport.getContentType(),filePath,id)
            );

            fileToImport.transferTo(new File(filePath));



        }else {
           throw  new IllegalStateException("No Such User");
        }




    }

    public void deleteFile(Long id) {

        Optional<GroupFile>found=this.fileRepository.findById(id);
        if(found.isPresent()){

           String path=found.get().getPath();



            File file=new File(path);
            if(file.delete())
                this.fileRepository.deleteById(id);
        }else throw  new IllegalStateException("Error in Deleteing File");

    }
}
