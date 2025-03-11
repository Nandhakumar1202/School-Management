package com.example.school_management.service;

import java.util.List;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
import com.example.school_management.dto.StudentMarksDto;
import com.example.school_management.entity.Questions;
import com.example.school_management.entity.StudentAnswer;
import com.example.school_management.repository.StudentAnswerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.Student;
import com.example.school_management.repository.StudentRepository;
import com.example.school_management.utilities.Constants;
import com.example.school_management.exception.UserNotFoundException;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	private final StudentAnswerRepository studentAnswerRepository;

	public StudentService(StudentRepository studentRepository, StudentAnswerRepository studentAnswerRepository) {
		this.studentRepository = studentRepository;
		this.studentAnswerRepository = studentAnswerRepository;
	}
	public ResponseDto createStudent(final Student student) {
		final Student students = this.studentRepository.save(student);
		return ResponseDto.builder()
				.message(Constants.CREATED)
				.data(students)
				.statusCode(HttpStatus.OK.value())
				.build();
	}
	public ResponseDto retrieveStudent() {
		final List<Student> student = this.studentRepository.findAll();
		return ResponseDto.builder()
				.message(Constants.RETRIEVED)
				.data(student)
				.statusCode(HttpStatus.OK.value())
				.build();
	}
	public ResponseDto getStudentById(final String id) {
	 final Student student = this.studentRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException("ID not found"));
	 return ResponseDto.builder()
			    .message(Constants.RETRIEVED)
			    .data(student)
			    .statusCode(HttpStatus.OK.value())
			    .build();
	}
	public ResponseDto updateStudent(final String id,final Student student) {
		final Student updateStudent = this.studentRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException("ID not found"));	
			updateStudent.setName(student.getName());
			updateStudent.setCity(student.getCity());
			updateStudent.setSchool(student.getSchool()); //to update the school for the particular student in the request.
			this.studentRepository.save(updateStudent);
			return ResponseDto.builder()
					.message(Constants.UPDATED)
					.data(updateStudent)
					.statusCode(HttpStatus.OK.value())
					.build();
	}
	public ResponseDto deleteStudent(final String id) {
		final Student deleteStudent = this.studentRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException("ID not found"));
		this.studentRepository.delete(deleteStudent);
		return ResponseDto.builder()
				.message(Constants.DELETED)
				.data(deleteStudent)
				.statusCode(HttpStatus.OK.value())
				.build();
	}
	public ResponseDto getMarkById (final String id){
		final Student student = this.studentRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException("ID not found"));
		final List <StudentAnswer> studentAnswers = this.studentAnswerRepository.findByStudentId(id);
		int marks = 0;
		for(StudentAnswer answer : studentAnswers){
			Questions questions = answer.getQuestions();
			if(questions != null && questions.getCorrectAnswer()!=null && answer.getStudentAnswer()!= null){
				if(answer.getStudentAnswer().equals(questions.getCorrectAnswer())){
					marks++;
				}
			}
		}
		StudentMarksDto studentMarksDto = new StudentMarksDto(student.getId(), student.getName(), marks);
				return ResponseDto.builder()
				.message(Constants.RETRIEVED)
				.data(studentMarksDto)
				.statusCode(HttpStatus.OK.value())
				.build();
	}



	/*public ResponseDto getAllMarks(){
		List<Student> students = studentRepository.findAll();
		Map<String,Integer> studentMarks = new HashMap<>();
		for(Student student : students){
			String studentId = student.getId();
			int marks = getMarksById(studentId);
			studentMarks.put(studentId,marks);
		}
		return ResponseDto.builder()
				.message(Constants.RETRIEVED)
				.data(studentMarks)
				.statusCode(HttpStatus.OK.value())
				.build();
	}*/

}
