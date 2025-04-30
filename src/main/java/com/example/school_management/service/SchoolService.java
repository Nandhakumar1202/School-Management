package com.example.school_management.service;

import com.example.school_management.dto.ResponseDto;
import com.example.school_management.dto.SchoolDetailsDto;
import com.example.school_management.entity.School;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Tutor;
import com.example.school_management.exception.UserNotFoundException;
import com.example.school_management.repository.SchoolRepository;
import com.example.school_management.repository.StudentRepository;
import com.example.school_management.repository.TutorRepository;
import com.example.school_management.utilities.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;

    public SchoolService(SchoolRepository schoolRepository, StudentRepository studentRepository,
                         TutorRepository tutorRepository) {
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
        this.tutorRepository = tutorRepository;
    }

    public ResponseDto createSchool(final School school) {
        final School schools = this.schoolRepository.save(school);
        return ResponseDto.builder()
                .message(Constants.CREATED)
                .data(schools)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseDto retrieveSchool() {
        final List<School> school = this.schoolRepository.findAll();
        return ResponseDto.builder()
                .message(Constants.RETRIEVED)
                .data(school)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseDto getSchoolById(final String id) {
        final School school = this.schoolRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID not found"));
        return ResponseDto.builder()
                .message(Constants.RETRIEVED)
                .data(school)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseDto updateSchool(final String id, final School school) {
        final School updateSchool = this.schoolRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID not found"));
        updateSchool.setName(school.getName());
        updateSchool.setCity(school.getCity());
        updateSchool.setEmail(school.getEmail());
        this.schoolRepository.save(updateSchool);
        return ResponseDto.builder()
                .message(Constants.UPDATED)
                .data(updateSchool)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseDto deleteSchool(final String id) {
        final School deleteSchool = this.schoolRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID not found"));
        this.schoolRepository.delete(deleteSchool);
        return ResponseDto.builder()
                .message(Constants.DELETED)
                .data(deleteSchool)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseDto getSchoolDetails(String id) {
        final School school = this.schoolRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID not found"));
        final List<Student> student = this.studentRepository.findBySchool(school);
        final List<Tutor> tutor = this.tutorRepository.findBySchool(school);
        final SchoolDetailsDto schoolDetailsDto = new SchoolDetailsDto(id, student, tutor);
        return ResponseDto.builder()
                .message(Constants.RETRIEVED)
                .data(schoolDetailsDto)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseDto getSchoolByPages(final int pageNumber, final int pageSize, final boolean order, final String name) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order ? Sort.Direction.ASC : Sort.Direction.DESC, name));
        Page<School> schoolPage = this.schoolRepository.findAll(pageable);
        return ResponseDto.builder()
                .message(Constants.RETRIEVED)
                .data(schoolPage)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
