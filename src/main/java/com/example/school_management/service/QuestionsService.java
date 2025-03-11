package com.example.school_management.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.Questions;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.QuestionsRepository;
import com.example.school_management.utilities.Constants;

@Service
public class QuestionsService {
    private final QuestionsRepository questionsRepository;

    public QuestionsService (QuestionsRepository questionsRepository){
        this.questionsRepository = questionsRepository;
    }
    public ResponseDto createQuestions(final Questions questions) {
        final Questions question = this.questionsRepository.save(questions);
        return ResponseDto.builder()
                .message(Constants.CREATED)
                .data(question)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
    public ResponseDto retrieveQuestions() {
        final List<Questions> questions = this.questionsRepository.findAll();
        return ResponseDto.builder()
                .message(Constants.RETRIEVED)
                .data(questions)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
    public ResponseDto getQuestionsById(final String id) {
        final Questions questions = this.questionsRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("ID not found"));
        return ResponseDto.builder()
                .message(Constants.RETRIEVED)
                .data(questions)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
    public ResponseDto updateQuestions(final String id,final Questions questions) {
        final Questions updateQuestions = this.questionsRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("ID not found"));
        updateQuestions.setQuestion(questions.getQuestion());
        updateQuestions.setChoice1(questions.getChoice1());
        updateQuestions.setChoice2(questions.getChoice2());
        updateQuestions.setChoice3(questions.getChoice3());
        updateQuestions.setCorrectAnswer(questions.getCorrectAnswer());
        updateQuestions.setTutor(questions.getTutor());
        this.questionsRepository.save(updateQuestions);
        return ResponseDto.builder()
                .message(Constants.UPDATED)
                .data(updateQuestions)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
    public ResponseDto deleteQuestions(final String id) {
        final Questions deleteQuestions = this.questionsRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("ID not found"));
        this.questionsRepository.delete(deleteQuestions);
        return ResponseDto.builder()
                .message(Constants.DELETED)
                .data(deleteQuestions)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
