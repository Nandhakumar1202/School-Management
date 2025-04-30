package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.School;
import com.example.school_management.service.SchoolService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/create")
    public ResponseDto createSchool(@RequestBody final School school) {
        return this.schoolService.createSchool(school);
    }

    @GetMapping("/retrieve")
    public ResponseDto retrieveAllSchool() {
        return this.schoolService.retrieveSchool();
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDto retrieveSchoolById(@PathVariable final String id) {
        return this.schoolService.getSchoolById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto updateSchool(@PathVariable final String id, @RequestBody final School school) {
        return this.schoolService.updateSchool(id, school);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDto removeSchool(@PathVariable final String id) {
        return this.schoolService.deleteSchool(id);
    }

    @GetMapping("/retrieve-school-details/{id}")
    public ResponseDto retrieveSchoolDetails(@PathVariable final String id) {
        return this.schoolService.getSchoolDetails(id);
    }

    @GetMapping("/retrieve-user-page")
    public ResponseDto retrieveUserPage(@RequestParam(defaultValue = "0") final int pageNumber,
                                        @RequestParam(defaultValue = "10") final int pageSize,
                                        @RequestParam final boolean order,
                                        @RequestParam final String name) {
        return this.schoolService.getSchoolByPages(pageNumber, pageSize, order, name);
    }
}