package com.example.school_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private String id;
    @Column(name = "question")
    private String question;
    @Column(name = "choice1")
    private String choice1;
    @Column(name = "choice2")
    private String choice2;
    @Column(name = "choice3")
    private String choice3;
    @Column(name = "correct answer")
    private String correctAnswer;
    @ManyToOne
    private Tutor tutor;
}