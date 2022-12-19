package com.example.demo.GroupFile;

import com.example.demo.Group.Group;

import com.example.demo.Group.GroupRepositroy;
import com.example.demo.Report.Report;
import com.example.demo.Report.ReportRepository;
import com.example.demo.Response.ResponseHandler;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import com.example.demo.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class FileService extends BaseService {

    private final FileRepository fileRepository;
    private final GroupRepositroy groupRepositroy;
    public final ReportRepository reportRepository;
    public final UserRepository userRepository;


    @Autowired
    public FileService(FileRepository fileRepository, GroupRepositroy groupRepositroy, ReportRepository reportRepository, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.groupRepositroy = groupRepositroy;
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }


    private final String path = "C:\\Users\\UsEr\\Desktop\\spring\\src\\static\\";


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

                Report report = new Report();
                GroupFile groupFile=new GroupFile(fileToImport.getOriginalFilename(),
                        fileToImport.getContentType(),
                        filePath,
                        id,
                        GroupFileStatus.Free.toString()
                );
                groupFile.setUser(getUser().getUser() );
                report.setGroupFile(fileRepository.save(groupFile));
                report.setUser(getUser().getUser());
                report.setType("CREATE");
                report.setLastModified(LocalDate.now());
                reportRepository.save(report);
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
            User user = getUser().getUser();
            if (found.isPresent()) {

                if (found.get().getFileStatus().equals(GroupFileStatus.Checked)
                        && !found.get().getCheckUserId().equals(user.getId())
                ) {
                    throw new IllegalStateException("Can't delete checked file");
                }

                String path = found.get().getPath();


                File file = new File(path);
                if (file.delete())
                    this.fileRepository.deleteById(id);
            } else throw new IllegalStateException("File Not Found!!");
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.FORBIDDEN, null);
        }
        return ResponseHandler.responseBuilder("DELETED", HttpStatus.OK, null);
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> checkFiles(List<Long> files) {

            User user = getUser().getUser();
            for (Long id : files) {
                Optional<GroupFile> found = this.fileRepository.findById(id);


                if (found.isPresent()) {

                    List<Long> groupUsersIds = this.userRepository.getAllByGroupId(found.get().getGroupId());
                    if(groupUsersIds.contains(getUser().getUser().getId())){

                    if (found.get().getFileStatus().equals(GroupFileStatus.Checked.toString())) {

                        throw new IllegalStateException("File :" +id+ "already checked");
                    } else {

                        found.get().setCheckUserId(user.getId());
                        found.get().setFileStatus(GroupFileStatus.Checked.toString());
                        fileRepository.save(found.get());

                        Report report = new Report();
                        report.setGroupFile(fileRepository.save(found.get()));
                        report.setUser(getUser().getUser());
                        report.setType("CHECk-IN");
                        report.setLastModified(LocalDate.now());
                        reportRepository.save(report);

                    }}else throw new IllegalStateException("File : "+id+" Can't be checked because you are not in group");


                } else throw new IllegalStateException("File : "+ id + "not found");

            }




        return ResponseHandler.responseBuilder("Files has been checked", HttpStatus.OK, null);
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> uncheckFile(List<Long> files) {
        Long userId = getUser().getUser().getId();


        for (Long id : files) {
            Optional<GroupFile> found = this.fileRepository.findById(id);

            if (found.isPresent()) {

                List<Long> groupUsersIds = this.userRepository.getAllByGroupId(found.get().getGroupId());
                if(groupUsersIds.contains(getUser().getUser().getId())){
                    if (found.get().getFileStatus().equals(GroupFileStatus.Free.toString())) {

                        throw new IllegalStateException("File : " +id + " is Already free");
                    } else {
                        if (!found.get().getCheckUserId().equals(userId)) {
                            throw new IllegalStateException("Only the user who check the file "+id+" can free it");
                        } else {

                            found.get().setFileStatus(GroupFileStatus.Free.toString());
                            found.get().setCheckUserId(null);
                            fileRepository.save(found.get());
                            Report report = new Report();
                            report.setGroupFile(fileRepository.save(found.get()));
                            report.setUser(getUser().getUser());
                            report.setType("CHECk-OUT");
                            report.setLastModified(LocalDate.now());
                            reportRepository.save(report);
                        }
                    }
                }else throw new IllegalStateException("File : "+id+" Can't be unchecked because you are not in group");



            } else throw new IllegalStateException("File "+id+" not found");

        }
        return ResponseHandler.responseBuilder("Files is free successfully", HttpStatus.OK, null);
    }

}

