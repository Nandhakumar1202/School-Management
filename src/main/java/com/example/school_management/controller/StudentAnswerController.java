package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.StudentAnswer;
import com.example.school_management.service.StudentAnswerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student-answer")

public class StudentAnswerController {
    private final StudentAnswerService studentAnswerService;

    public StudentAnswerController(StudentAnswerService studentAnswerService) {
        this.studentAnswerService = studentAnswerService;
    }

    @PostMapping("/create")
    public ResponseDto createStudentAnswer(@RequestBody final StudentAnswer studentAnswer) {
        return this.studentAnswerService.createStudentAnswer(studentAnswer);
    }

    @GetMapping("/retrieve")
    public ResponseDto retrieveAllStudentAnswer() {
        return this.studentAnswerService.retrieveStudentAnswer();
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDto retrieveStudentAnswerById(@PathVariable final String id) {
        return this.studentAnswerService.getStudentAnswerById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto updateStudentAnswer(@PathVariable final String id, @RequestBody final StudentAnswer studentAnswer) {
        return this.studentAnswerService.updateStudentAnswer(id, studentAnswer);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDto removeStudentAnswer(@PathVariable final String id) {
        return this.studentAnswerService.deleteStudentAnswer(id);
    }
}
