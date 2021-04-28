package com.example.demo_jpa.services;

import com.example.demo_jpa.entities.Student;

import java.util.List;

public interface IStudentService {

    void saveStudent(Student student);
    void deleteStudent(Long id);
    Student getStudentById(Long id);
    List<Student> getStudents();
    // void updateStudent(Student student);

}
