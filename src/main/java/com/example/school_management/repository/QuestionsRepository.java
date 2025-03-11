package com.example.school_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.school_management.entity.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, String> {

}
