package com.example.demo.File;

import com.example.demo.Report.Report;
import com.example.demo.Group.Group;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "files")
@Table(
        name = "files"
)
public class File {
    public File() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Id
    @SequenceGenerator(
            name = "sequence_files",
            sequenceName = "sequence_files",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "sequence_files"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", updatable = false, nullable = false)
    private String name;
    @Column(name = "url", nullable = false)
    private String url;



    @Column(name = "status", nullable = false,updatable = true)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group.id")
    private Group group;
    @OneToMany(mappedBy = "file", fetch = FetchType.LAZY, orphanRemoval = true)
    public List<Report> reportList= new ArrayList<>();

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public File(String name, String url, String status, Group group, List<Report> reportList) {
        this.name = name;
        this.url = url;
        this.status = status;
        this.group = group;
        this.reportList = reportList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
