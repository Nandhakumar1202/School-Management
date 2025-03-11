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
import com.example.school_management.entity.Courses;
import com.example.school_management.service.CoursesService;

@RestController
@RequestMapping("/courses")

public class CoursesController {

    private final CoursesService coursesService;
    public CoursesController(CoursesService coursesService){
        this.coursesService = coursesService;
    }
    @PostMapping("/create")
    public ResponseDto createCourses(@RequestBody final Courses courses) {
        return this.coursesService.createCourses(courses);
    }
    @GetMapping("/retrieve")
    public ResponseDto retrieveAllCourses() {
        return this.coursesService.retrieveCourses();
    }
    @GetMapping("/retrieve/{id}")
    public ResponseDto retrieveCoursesById(@PathVariable final String id) {
        return this.coursesService.getCoursesById(id);
    }
    @PutMapping("/update/{id}")
    public ResponseDto updateCourses(@PathVariable final String id,@RequestBody final Courses courses) {
        return this.coursesService.updateCourses(id, courses);
    }
    @DeleteMapping("/remove/{id}")
    public ResponseDto removeCourses(@PathVariable final String id) {
        return this.coursesService.deleteCourses(id);
    }
}

