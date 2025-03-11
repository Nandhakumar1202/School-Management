package com.example.school_management.repository;

import com.example.school_management.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.school_management.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
       List<Student> findBySchool(School school);
}
