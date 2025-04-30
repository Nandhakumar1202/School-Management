package com.example.school_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private String id;
    @Column(name = "subject name")
    private String subjectName;
    @ManyToOne
    private School school;
    @ManyToOne
    private Tutor tutor;
}