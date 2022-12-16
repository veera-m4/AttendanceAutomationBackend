package com.project.Controller;


import com.project.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "**", allowedHeaders = "*", allowCredentials = "True")
public class ClassController {
    @Autowired
    ClassService classService;
    @GetMapping("/classreport/")
}
