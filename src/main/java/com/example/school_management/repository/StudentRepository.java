package com.example.school_management.repository;

import com.example.school_management.entity.School;
import com.example.school_management.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findBySchool(School school);

    @Query("SELECT student FROM Student student JOIN student.school s WHERE student.id LIKE %:search% " +
            "or student.name LIKE %:search% or student.city LIKE %:search% " +
            "or s.name LIKE %:search% or s.city LIKE %:search% or s.id LIKE %:search% " +
            "or s.email LIKE %:search%")
    Page<Student> findStudentByNameOrCity(@Param("search") String search, Pageable pageable);
}

/*    @Query("SELECT a FROM Student a WHERE a.name LIKE %:name%")
/*    List<Student> findStudentByNameStudent(@Param("name") String name);

/*    @Query("SELECT a FROM Student a WHERE a.name LIKE %:name% AND a.city LIKE %:city%")
//    List<Student> findStudentByName(@Param("name") String name,
//                                    @Param("city") String city);
//@Query("SELECT a FROM Student a WHERE a.name LIKE %:search% OR a.city LIKE %:search%")
List<Student> findStudentByNameOrCity(@Param("search") String search);
*/