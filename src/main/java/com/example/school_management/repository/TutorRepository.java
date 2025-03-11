package com.example.school_management.repository;

import com.example.school_management.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.school_management.entity.Tutor;

import java.util.List;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, String> {
    List<Tutor> findBySchool(School school);

}
