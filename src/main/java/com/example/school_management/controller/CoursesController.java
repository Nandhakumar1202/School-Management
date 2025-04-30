package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.Courses;
import com.example.school_management.service.CoursesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("courses")
public class CoursesController {
    private final CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
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
    public ResponseDto updateCourses(@PathVariable final String id, @RequestBody final Courses courses) {
        return this.coursesService.updateCourses(id, courses);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDto removeCourses(@PathVariable final String id) {
        return this.coursesService.deleteCourses(id);
    }
}