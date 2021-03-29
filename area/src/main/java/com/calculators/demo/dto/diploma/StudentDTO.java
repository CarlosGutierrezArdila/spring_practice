package com.calculators.demo.dto.diploma;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentDTO {
    private String name;
    private List<SubjectDTO> subjects;

}
