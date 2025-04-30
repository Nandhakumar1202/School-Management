package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.Questions;
import com.example.school_management.entity.Tutor;
import com.example.school_management.service.TutorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutor")
public class TutorController {
    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/create")
    public ResponseDto createTutor(@RequestBody final Tutor tutor) {
        return this.tutorService.createTutor(tutor);
    }

    @GetMapping("/retrieve")
    public ResponseDto retrieveAllTutor() {
        return this.tutorService.retrieveTutor();
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDto retrieveTutorById(@PathVariable final String id) {
        return this.tutorService.getTutorById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto updateTutor(@PathVariable final String id, @RequestBody final Tutor tutor) {
        return this.tutorService.updateTutor(id, tutor);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDto removeTutor(@PathVariable final String id) {
        return this.tutorService.deleteTutor(id);
    }

    @GetMapping("/retrieve-tutor-evaluation")
    public ResponseDto retrieveAllStudentsMarks() {
        return this.tutorService.getAllStudentsMarks();
    }

    @PostMapping("/create-questionsAndChoices")
    public ResponseDto createQuestionsAndChoices(@RequestBody final Questions questions) {
        return this.tutorService.addQuestionsChoicesByTutor(questions);
    }

    @GetMapping("/retrieve-user-page")
    public ResponseDto retrieveUserPage(@RequestParam(defaultValue = "0") final int pageNumber,
                                        @RequestParam(defaultValue = "10") final int pageSize,
                                        @RequestParam final boolean order,
                                        @RequestParam final String name) {
        return this.tutorService.getTutorByPages(pageNumber, pageSize, order, name);
    }
}