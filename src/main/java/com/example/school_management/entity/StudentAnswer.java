package com.example.school_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student_answer")


public class StudentAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private String id;
	
	@Column(name = "student_answer")
	private String studentAnswer;

	@ManyToOne
	private Questions questions;
	@ManyToOne
	private Student student;
}
