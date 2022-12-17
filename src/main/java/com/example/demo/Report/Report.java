package com.example.demo.Report;

import com.example.demo.GroupFile.GroupFile;
import com.example.demo.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "reports")
@Table(
        name = "reports"
)
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_file.id")
    private GroupFile groupFile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users.id")
    private User user;
    private String type;
    private LocalDate lastModified;
}
