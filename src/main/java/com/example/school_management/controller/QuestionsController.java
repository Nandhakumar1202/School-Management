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
import com.example.school_management.entity.Questions;
import com.example.school_management.service.QuestionsService;

@RestController
@RequestMapping("/questions")

public class QuestionsController {

    private final QuestionsService questionsService;
    public QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @PostMapping("/create")
    public ResponseDto createQuestions(@RequestBody final Questions questions) {
        return this.questionsService.createQuestions(questions);
    }
    @GetMapping("/retrieve")
    public ResponseDto retrieveAllQuestions() {
        return this.questionsService.retrieveQuestions();
    }
    @GetMapping("/retrieve/{id}")
    public ResponseDto retrieveQuestionsById(@PathVariable final String id) {
        return this.questionsService.getQuestionsById(id);
    }
    @PutMapping("/update/{id}")
    public ResponseDto updateQuestions(@PathVariable final String id,@RequestBody final Questions questions) {
        return this.questionsService.updateQuestions(id, questions);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDto removeQuestions(@PathVariable final String id) {
        return this.questionsService.deleteQuestions(id);
    }
}

