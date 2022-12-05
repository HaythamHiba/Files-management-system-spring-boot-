package com.example.demo.GroupFile;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class FileDTO {
    @NotEmpty
    private String name;
    @NotNull
    private Long group_id;
    @NotNull
    private MultipartFile file;

    public FileDTO(String name, Long group_id, MultipartFile file) {
        this.name = name;
        this.group_id = group_id;
        this.file = file;
    }

    public FileDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
