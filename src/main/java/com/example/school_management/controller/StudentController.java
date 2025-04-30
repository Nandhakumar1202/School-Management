package com.example.school_management.controller;

import com.example.school_management.dto.ResponseDto;
import com.example.school_management.entity.Student;
import com.example.school_management.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseDto createStudent(@RequestBody final Student student) {
        return this.studentService.createStudent(student);
    }

    @GetMapping("/retrieve")
    public ResponseDto retrieveAllStudent() {
        return this.studentService.retrieveStudent();
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDto retrieveStudentById(@PathVariable final String id) {
        return this.studentService.getStudentById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto updateStudent(@PathVariable final String id, @RequestBody final Student student) {
        return this.studentService.updateStudent(id, student);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDto removeStudent(@PathVariable final String id) {
        return this.studentService.deleteStudent(id);
    }

    @GetMapping("/retrieve-student-mark/{id}")
    public ResponseDto retrieveMarkById(@PathVariable final String id) {
        return this.studentService.getMarkById(id);
    }


    @GetMapping("/retrieve-user-page")
    public ResponseDto retrieveUserPage(@RequestParam(defaultValue = "0") final int pageNumber,
                                        @RequestParam(defaultValue = "10") final int pageSize,
                                        @RequestParam(defaultValue = "true") final boolean order,
                                        @RequestParam(defaultValue = "name") final String name,
                                        @RequestParam(defaultValue = "") final String search) {
        return this.studentService.getStudentBySortingAndSearching(pageNumber, pageSize, order, name, search);
    }

}

/*    @GetMapping("/retrieve-user-page")
//    public ResponseDto retrieveUserPage(@RequestParam(defaultValue = "0") final int pageNumber,
//                                        @RequestParam(defaultValue = "10") final int pageSize,
//                                        @RequestParam final boolean order,
//                                        @RequestParam final String name) {
//        return this.studentService.getStudentByPages(pageNumber, pageSize, order, name);
//    }
//    @GetMapping("/get-by-name")
//    public List<Student> getStudentByName(@RequestParam final String search){
//        return this.studentService.getStudentByName(search);
//    }

/*@GetMapping("/get-page")
public Page<Student> retrievePage(@RequestParam(defaultValue = "1") final int pageIndex,
                                  @RequestParam(defaultValue = "3") final int pageSize){
    return this.studentService.getStudentPage(pageIndex, pageSize);
}
*/