package com.example.school_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "city")
    private String city;
    @ManyToOne
    private School school;
}