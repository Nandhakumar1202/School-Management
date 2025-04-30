package com.example.school_management.service;

import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.Questions;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.StudentAnswer;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.QuestionsRepository;
import com.example.school_management.repository.StudentAnswerRepository;
import com.example.school_management.repository.StudentRepository;
import com.example.school_management.utilities.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAnswerService {
    private final StudentAnswerRepository studentAnswerRepository;
    private final StudentRepository studentRepository;
    private final QuestionsRepository questionsRepository;

    public StudentAnswerService(StudentAnswerRepository studentAnswerRepository, StudentRepository studentRepository,
                                QuestionsRepository questionsRepository) {
        this.studentAnswerRepository = studentAnswerRepository;
        this.studentRepository = studentRepository;
        this.questionsRepository = questionsRepository;
    }

    public ResponseDto createStudentAnswer(final StudentAnswer studentAnswer) {
        final Student studentIsPresent = this.studentRepository.findById(studentAnswer.getStudent().getId())
                .orElseThrow(() -> new UserNotFoundException("ID not found"));//check id is present or not
        final Questions questionIsPresent = this.questionsRepository.findById(studentAnswer.getQuestions().getId())
                .orElseThrow(() -> new UserNotFoundException("ID not found"));//check id is present or not
        studentAnswer.setStudent(studentIsPresent);
        studentAnswer.setQuestions(questionIsPresent);
        if (questionIsPresent.getChoice1().equals(studentAnswer.getStudentAnswer())
                || questionIsPresent.getChoice2().equals(studentAnswer.getStudentAnswer())
                || questionIsPresent.getChoice3().equals(studentAnswer.getStudentAnswer())) {
            final StudentAnswer studentAnswers = this.studentAnswerRepository.save(studentAnswer);//check the given choice is present in the 3 choices
            return ResponseDto.builder()
                    .message(Constants.CREATED)
                    .data(studentAnswers)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } else {
            throw new UserNotFoundException("choice not matched with answer");
        }
    }

    public ResponseDto retrieveStudentAnswer() {
        final List<StudentAnswer> studentAnswer = this.studentAnswerRepository.findAll();
        return ResponseDto.builder()
                .message(Constants.RETRIEVED)
                .data(studentAnswer)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseDto getStudentAnswerById(final String id) {
        final StudentAnswer studentAnswer = this.studentAnswerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID not found"));
        return ResponseDto.builder()
                .message(Constants.RETRIEVED)
                .data(studentAnswer)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseDto updateStudentAnswer(final String id, final StudentAnswer studentAnswer) {
        final StudentAnswer updateStudentAnswer = this.studentAnswerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID not found"));
        updateStudentAnswer.setStudentAnswer(studentAnswer.getStudentAnswer());
        updateStudentAnswer.setQuestions(studentAnswer.getQuestions());
        updateStudentAnswer.setStudent(studentAnswer.getStudent());
        this.studentAnswerRepository.save(updateStudentAnswer);
        return ResponseDto.builder()
                .message(Constants.UPDATED)
                .data(updateStudentAnswer)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseDto deleteStudentAnswer(final String id) {
        final StudentAnswer deleteStudentAnswer = this.studentAnswerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID not found"));
        this.studentAnswerRepository.delete(deleteStudentAnswer);
        return ResponseDto.builder()
                .message(Constants.DELETED)
                .data(deleteStudentAnswer)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}