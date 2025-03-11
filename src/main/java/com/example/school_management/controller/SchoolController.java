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
import com.example.school_management.entity.School;
import com.example.school_management.service.SchoolService;

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
    public ResponseDto updateSchool(@PathVariable final String id,@RequestBody final School school) {
    	return this.schoolService.updateSchool(id, school);
    }
    
    @DeleteMapping("/remove/{id}")
    public ResponseDto removeSchool(@PathVariable final String id) {
    	return this.schoolService.deleteSchool(id);
    }
	@GetMapping("/retrieve-school-details/{id}")
	public ResponseDto retrieveSchoolDetails(@PathVariable final String id){
		return this.schoolService.getSchoolDetails(id);
	}
}
