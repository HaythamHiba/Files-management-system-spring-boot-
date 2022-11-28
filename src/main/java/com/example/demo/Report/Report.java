package com.example.demo.Report;

import com.example.demo.File.File;
import com.example.demo.User.User;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "reports")
@Table(
        name = "reports"
)
public class Report {
    @Id
    @SequenceGenerator(
            name = "sequence_reports",
            sequenceName = "sequence_reports",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "sequence_reports"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file.id")
    private File file;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.id")
    private User user;
    private Date lastModified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }


    public Report(File file, User user, Date lastModified) {
        this.file = file;
        this.user = user;
        this.lastModified = lastModified;
    }

    public Report() {
    }
}
