package com.example.demo.Report;

import com.example.demo.GroupFile.GroupFile;
import com.example.demo.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

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
    private String type;
    private LocalDate lastModified;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @ManyToOne(fetch = FetchType.LAZY)

    private GroupFile groupFile;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @ManyToOne(fetch = FetchType.LAZY)

    private User user;
}
