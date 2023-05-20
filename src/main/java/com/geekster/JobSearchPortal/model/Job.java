package com.geekster.JobSearchPortal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotNull(message = "location should not be null")
    private String location;

    private Double salary;

    @NotEmpty
    private String companyName;

    @NotEmpty
    private String employerName;

    @Enumerated(EnumType.STRING)
    private JobType jobType;
}
