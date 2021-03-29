package com.calculators.demo.controllers;

import com.calculators.demo.dto.diploma.StudentDTO;
import com.calculators.demo.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diploma")
public class DiplomaController {
    @PostMapping("/generateDiploma")
    public ResponseEntity generateDiploma(@RequestBody StudentDTO student){
        return new ResponseEntity(StudentService.generateDiploma(student), HttpStatus.OK);
    }
}
