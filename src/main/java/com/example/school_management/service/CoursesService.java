package com.example.school_management.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.Courses;
import com.example.school_management.repository.CoursesRepository;
import com.example.school_management.utilities.Constants;
import com.example.school_management.exception.UserNotFoundException;

@Service
public class CoursesService {
	private final CoursesRepository coursesRepository;

	public CoursesService(CoursesRepository coursesRepository){
		this.coursesRepository = coursesRepository;
	}
	public ResponseDto createCourses(final Courses courses) {
		final Courses course = this.coursesRepository.save(courses);
		return ResponseDto.builder()
				.message(Constants.CREATED)
				.data(course)
				.statusCode(HttpStatus.OK.value())
				.build();
	}
	public ResponseDto retrieveCourses() {
		final List<Courses> courses = this.coursesRepository.findAll();
		return ResponseDto.builder()
				.message(Constants.RETRIEVED)
				.data(courses)
				.statusCode(HttpStatus.OK.value())
				.build();
	}
	public ResponseDto getCoursesById(final String id) {
		final Courses courses = this.coursesRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException("ID not found"));
	 return ResponseDto.builder()
			    .message(Constants.RETRIEVED)
			    .data(courses)
			    .statusCode(HttpStatus.OK.value())
			    .build();
	}
	public ResponseDto updateCourses(final String id,final Courses courses) {
		final Courses updateCourses = this.coursesRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException("ID not found"));	
			updateCourses.setSubjectName(courses.getSubjectName());
			updateCourses.setSchool(courses.getSchool());
		    updateCourses.setTutor(courses.getTutor());
			this.coursesRepository.save(updateCourses);
			return ResponseDto.builder()
					.message(Constants.UPDATED)
					.data(updateCourses)
					.statusCode(HttpStatus.OK.value())
					.build();
	}
	public ResponseDto deleteCourses(final String id) {
		final Courses deleteCourses = this.coursesRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException("ID not found"));
		this.coursesRepository.delete(deleteCourses);
		return ResponseDto.builder()
				.message(Constants.DELETED)
				.data(deleteCourses)
				.statusCode(HttpStatus.OK.value())
				.build();
	}
}
