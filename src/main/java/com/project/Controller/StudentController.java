package com.project.Controller;

import com.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@CrossOrigin(origins = "**", allowedHeaders = "*", allowCredentials = "True")
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping("/studentdetails/{rollno}")
    public Map<String, Object> getStudentDetails(@PathVariable String rollno)
    {
        return studentService.studentDetails(rollno);
    }
}
