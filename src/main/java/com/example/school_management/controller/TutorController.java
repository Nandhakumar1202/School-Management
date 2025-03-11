package com.example.school_management.controller;

import com.example.school_management.entity.Questions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.Tutor;
import com.example.school_management.service.TutorService;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    private final TutorService tutorService;
    public TutorController(TutorService tutorService){
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
    public ResponseDto updateTutor(@PathVariable final String id,@RequestBody final Tutor tutor) {
        return this.tutorService.updateTutor(id, tutor);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDto removeTutor(@PathVariable final String id) {
        return this.tutorService.deleteTutor(id);
    }

    @GetMapping ("/retrieve-tutor-evaluation")
    public ResponseDto retrieveAllStudentsMarks(){
        return this.tutorService.getAllStudentsMarks();
    }
    @PostMapping("/create-questionsAndChoices")
    public ResponseDto createQuestionsAndChoices(@RequestBody final Questions questions){
        return this.tutorService.addQuestionsChoicesByTutor(questions);
    }
}



