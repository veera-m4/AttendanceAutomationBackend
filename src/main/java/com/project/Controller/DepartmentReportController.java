package com.project.Controller;


import com.project.service.DepartmentService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "**", allowedHeaders = "*", allowCredentials = "True")
public class DepartmentReportController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping("/getDepartmentReport/{departId}/{date}")
    public List<Map<String,Object>> getDepartmentBYDate(@PathVariable Long departId, @PathVariable Date date) throws FileNotFoundException, ParseException {
        return departmentService.departmentReport(departId,date);
    }
    @GetMapping("/getDailyDepartmentreport/{departId}")
    public List<Map<String, Object>> getDailyDepartment(@PathVariable Long departId)
    {
        return  departmentService.
    }
}
