package com.calculators.demo.services;

import com.calculators.demo.dto.diploma.DiplomaDTO;
import com.calculators.demo.dto.diploma.StudentDTO;
import com.calculators.demo.dto.diploma.SubjectDTO;

public class StudentService {
    public static DiplomaDTO generateDiploma(StudentDTO student) {
        Double sum = 0.0;
        for (SubjectDTO subject : student.getSubjects()) {
            sum += subject.getGrade();
        }
        double average = sum / student.getSubjects().size();
        return new DiplomaDTO(String.format("La universidad X otorga el diploma a %s con promedio de %f", student.getName(), average) + (average > 9.0 ? " Graduado con honores felicidades" : ""));
    }
}
