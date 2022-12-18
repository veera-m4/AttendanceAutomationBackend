package com.project.Controller;


import com.project.service.ClassService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "**", allowedHeaders = "*", allowCredentials = "True")
public class ClassDataSenderController {
    @Autowired
    ClassService classService;
    @GetMapping("/classleavedailyreport/{classid}")
    public Map<String, List<Map<String, Object>>> getdailyClassdeatails(@PathVariable Long classid) throws FileNotFoundException, ParseException {
        return classService.getDailyAttendanceLeaveAndOd(classid);
    }


    @GetMapping("/classtotalReport/{classid}/{sem}")
    public List<Map<String, Object>> getTotalClassreport(@PathVariable Long classid, @PathVariable int sem)
    {
        return classService.getSemesterStudentAttendancePercentage(classid,sem);
    }
}
