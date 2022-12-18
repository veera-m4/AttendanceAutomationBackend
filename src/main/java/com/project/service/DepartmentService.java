package com.project.service;

import com.project.Model.ClassTable;
import com.project.Model.DailyDepartmentReport;
import com.project.Model.FacultyTable;
import com.project.repository.ClassTableRepo;
import com.project.repository.DailyDepartmentReportRepo;
import com.project.repository.FacultyTableRepo;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static com.project.UtilityFIle.getJsonFileAsList;

@Component
@Service
public class DepartmentService {
    @Autowired
    ClassTableRepo classTableRepo;
    @Autowired
    DailyDepartmentReportRepo dailyDepartmentReportRepo;
    @Autowired
    ClassService classService;
    @Autowired
    FacultyTableRepo facultyTableRepo;
    private final String baseDir = System. getProperty("user.dir");

    public List<Map<String,Object>> departmentReport(Long deptId, Date d) throws FileNotFoundException, ParseException {
        return getJsonFileAsList(deptId, d, baseDir);
    }
    private List<Map<String, Object>> getDailyDepartmentReport(Long departId) throws FileNotFoundException, ParseException {
        List<Map<String,Object>> result = new ArrayList<>();
        List<ClassTable> classList =  classTableRepo.findAllByDepartmentId(departId);
        for(ClassTable classDetails :  classList)
        {
            FacultyTable tutorDetails =  facultyTableRepo.findFacultyTableByFacultyId(classDetails.getTutorId());
            Map<String, List<Map<String, Object>>> classDailyReport =  classService.getDailyAttendanceLeaveAndOd(classDetails.getId());
            Map<String, Object> classDetailReport = new HashMap<>();
            classDetailReport.put("Batch" , classDetails.getBatch());
            classDetailReport.put("Semester",classDetails.getCompletedSemester()+1);
            classDetailReport.put("Total Number of Student",classDetails.getTutorId());
            classDetailReport.put("Number Student on duty",classDailyReport.get("Onduty List").size());
            classDetailReport.put("Number Student on Absent",classDailyReport.get("Absentees List").size());
            classDetailReport.put("Number of Approved Leaves", classDailyReport.get("Approved Leaves").size());
            result.add(classDetailReport);
        }
        return result;
    }



}
