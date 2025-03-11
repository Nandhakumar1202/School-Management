package com.example.school_management.dto;

import lombok.Data;
@Data

public class StudentMarksDto {
        private String id;
        private String name;
        private int marks;

        public StudentMarksDto(String id, String name, int marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }

}
