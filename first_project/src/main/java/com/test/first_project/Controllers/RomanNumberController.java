package com.test.first_project.Controllers;

import com.test.first_project.Entities.RomanNumber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roman")
public class RomanNumberController {

    @GetMapping("/convert")
    public RomanNumber convertToRoman(@RequestParam(value = "number") Integer number){
        try {
            return new RomanNumber(number);
        }catch (Exception e){
            return  new RomanNumber(number, "Error: "+e.getMessage());
        }
    }
}
