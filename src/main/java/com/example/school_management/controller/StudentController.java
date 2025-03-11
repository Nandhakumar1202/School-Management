package com.example.school_management.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.Student;
import com.example.school_management.service.StudentService;

@RestController
@RequestMapping("/student")

public class StudentController {
	
	private final StudentService studentService;
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/create")
		public ResponseDto createStudent(@RequestBody final Student student) {
		return this.studentService.createStudent(student);
	}
	@GetMapping("/retrieve")
	public ResponseDto retrieveAllStudent() {
		return this.studentService.retrieveStudent();
	}
	
    @GetMapping("/retrieve/{id}")
    public ResponseDto retrieveStudentById(@PathVariable final String id) {
		return this.studentService.getStudentById(id);
    }
   
    @PutMapping("/update/{id}")
    public ResponseDto updateStudent(@PathVariable final String id,@RequestBody final Student student) {
    	return this.studentService.updateStudent(id, student);
    }
    
    @DeleteMapping("/remove/{id}")
    public ResponseDto removeStudent(@PathVariable final String id) {
    	return this.studentService.deleteStudent(id);
    }

	@GetMapping ("/retrieve-student-mark/{id}")
	public ResponseDto retrieveMarkById(@PathVariable final String id){
		return this.studentService.getMarkById(id);
	}
}
