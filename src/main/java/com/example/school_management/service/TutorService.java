package com.example.school_management.service;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import com.example.school_management.dto.StudentMarksDto;
import com.example.school_management.entity.Questions;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.StudentAnswer;
import com.example.school_management.repository.QuestionsRepository;
import com.example.school_management.repository.StudentAnswerRepository;
import com.example.school_management.repository.StudentRepository;
//import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.Tutor;
import com.example.school_management.repository.TutorRepository;
import com.example.school_management.utilities.Constants;
import com.example.school_management.exception.UserNotFoundException;

@Service
public class TutorService {
	private final TutorRepository tutorRepository;
	private final StudentRepository studentRepository;
	private final StudentAnswerRepository studentAnswerRepository;
	private final QuestionsRepository questionsRepository;

	public TutorService(TutorRepository tutorRepository,StudentRepository studentRepository
			,StudentAnswerRepository studentAnswerRepository,QuestionsRepository questionsRepository){
		this.tutorRepository = tutorRepository;
		this.studentRepository = studentRepository;
		this.studentAnswerRepository = studentAnswerRepository;
		this.questionsRepository = questionsRepository;
	}
	public ResponseDto createTutor(final Tutor tutor) {
		final Tutor tutors = this.tutorRepository.save(tutor);
		return ResponseDto.builder()
				.message(Constants.CREATED)
				.data(tutors)
				.statusCode(HttpStatus.OK.value())
				.build();
	}
	public ResponseDto retrieveTutor() {
		final List<Tutor> tutor = this.tutorRepository.findAll();
		return ResponseDto.builder()
				.message(Constants.RETRIEVED)
				.data(tutor)
				.statusCode(HttpStatus.OK.value())
				.build();
	}
	public ResponseDto getTutorById(final String id) {
		final Tutor tutor = this.tutorRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException("ID not found"));
	    return ResponseDto.builder()
			    .message(Constants.RETRIEVED)
				.data(tutor)
			    .statusCode(HttpStatus.OK.value())
			    .build();
	}
	public ResponseDto updateTutor(final String id,final Tutor tutor) {
		final Tutor updateTutor = this.tutorRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException("ID not found"));	
			updateTutor.setName(tutor.getName());
			updateTutor.setSchool(tutor.getSchool());
			this.tutorRepository.save(updateTutor);
			return ResponseDto.builder()
					.message(Constants.UPDATED)
					.data(updateTutor)
					.statusCode(HttpStatus.OK.value())
					.build();
	}
	public ResponseDto deleteTutor(final String id) {
		final Tutor deleteTutor = this.tutorRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException("ID not found"));
		this.tutorRepository.delete(deleteTutor);
		return ResponseDto.builder()
				.message(Constants.DELETED)
				.data(deleteTutor)
				.statusCode(HttpStatus.OK.value())
				.build();
	}
	public int evaluateMarks(final String id){
		final List <StudentAnswer> answer = this.studentAnswerRepository.findByStudentId(id);
		int marks = 0;
		for (StudentAnswer studentAnswer : answer) {
			Questions questions = studentAnswer.getQuestions();
			if (questions != null && studentAnswer.getStudentAnswer() != null && questions.getCorrectAnswer() != null) {
				if (studentAnswer.getStudentAnswer().equals(questions.getCorrectAnswer())) {
					marks++;
				}
			}
		}
		return marks;
	}

//	public ResponseDto getAllStudentMarks() {
//		List<Student> students = studentRepository.findAll();
//		Map<String,Integer> studentMarks = new HashMap<>();
//		for (Student student : students) {
//			String studentId = student.getId();
//			int marks = evaluateMarks(studentId);
//			studentMarks.put(studentId, marks);
//		}
//		return ResponseDto.builder()
//				.message(Constants.RETRIEVED)
//				.data(studentMarks)
//				.statusCode(HttpStatus.OK.value())
//				.build();
//	}

	public ResponseDto getAllStudentsMarks() {
		final List<Student> students = this.studentRepository.findAll();
		final List<StudentMarksDto> studentMarksList = new ArrayList<>();

		for (Student student : students) {
			final String studentId = student.getId();
			final String studentName = student.getName();
			final int marks = evaluateMarks(studentId);

			StudentMarksDto studentMarksDto = new StudentMarksDto(studentId, studentName, marks);
			studentMarksList.add(studentMarksDto);
		}
		return ResponseDto.builder()
				.message(Constants.RETRIEVED)
				.data(studentMarksList)
				.statusCode(HttpStatus.OK.value())
				.build();
	}
	public ResponseDto addQuestionsChoicesByTutor(final Questions questions) {
		final Questions newQuestions = this.questionsRepository.save(questions);
		return ResponseDto.builder()
				.message(Constants.CREATED)
				.data(newQuestions)
				.statusCode(HttpStatus.OK.value())
				.build();
	}

}
