package com.example.demo_jpa.controllers;

import com.example.demo_jpa.entities.Student;
import com.example.demo_jpa.services.IStudentService;
import com.example.demo_jpa.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    private ResponseEntity saveStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        return ResponseEntity.ok().body(student);
    }


    @GetMapping("/{id}")
    private ResponseEntity findStudent(@PathVariable Long id){
        return ResponseEntity.ok().body(studentService.getStudentById(id));
    }

    @PatchMapping("/{id}")
    private ResponseEntity update1(@PathVariable Long id, @RequestParam String name, @RequestParam String lastName ){
        Student student = studentService.getStudentById(id);
        student.setName(name);
        student.setLast_name(lastName);
        studentService.saveStudent(student);
        return ResponseEntity.ok().body(student);
    }

    @PutMapping()
    private ResponseEntity update2(@RequestBody Student student){
        studentService.saveStudent(student);
        return ResponseEntity.ok().body("Succesfully updated");
    }

    @GetMapping
    private ResponseEntity getAll(){
        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }





}
