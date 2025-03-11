package com.example.school_management.dto;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Tutor;
import lombok.Data;
import java.util.List;
@Data

public class SchoolDetailsDto {
    private String id;
    private List<Student> student ;
    private List<Tutor> tutor;

    public SchoolDetailsDto(String id, List<Student> student, List<Tutor> tutor) {
        this.id = id;
        this.student = student;
        this.tutor = tutor;
    }


}
