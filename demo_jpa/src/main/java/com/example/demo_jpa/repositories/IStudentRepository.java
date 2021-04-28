package com.example.demo_jpa.repositories;

import com.example.demo_jpa.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student,Long> {

}
