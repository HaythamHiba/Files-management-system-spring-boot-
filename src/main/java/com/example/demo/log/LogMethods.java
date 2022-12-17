package com.example.demo.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class LogMethods  {
    @Id
    @SequenceGenerator(
            name = "sequence_log",
            sequenceName = "sequence_log",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "sequence_log"
    )
    private Long Id;
    @Column(name = "message", length = 3000)
    private String message;

    public LogMethods(String message) {
        this.message = message;
    }
}
