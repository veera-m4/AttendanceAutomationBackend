package com.project.service;

import com.project.Model.*;
import com.project.repository.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

import static com.project.UtilityFIle.writeInTheFile;

@Component
@Service
public class FileWriteService {
    @Autowired
    TodayAttendanceStatusRepo todayAttendanceStatusRepo;
    @Autowired
    StudentTableRepo studentTableRepo;
    @Autowired
    ClassTableRepo classTableRepo;
    @Autowired
    DailyDepartmentReportRepo dailyDepartmentReportRepo;

    @Autowired
    StudentAttendanceRepo studentAttendanceRepo;

    private final String baseDir = System. getProperty("user.dir");

    public void writeReportForClass(Long classId)
    {
        int absent=0;
        int approvedLeave=0;
        int onDuty=0;
        double percentage=0;
        int studentCount=0;
        String dir = baseDir+ File.separator+classId;
        JSONObject jsonObject = new JSONObject();
        int currentSemester = classTableRepo.findClassTableById(classId).getCompletedSemester();
        currentSemester++;
        List<StudentTable> studentTableList = studentTableRepo.findAllByClassid(classId);
        studentCount = studentTableList.size();
        List<Map<String, Object>> result = new ArrayList<>();
        for(StudentTable studentTable : studentTableList)
        {
            StudentAttendance studentAttendance =  studentAttendanceRepo.findStudentAttendanceByStudentRollNumberAndSemester(studentTable.getRollNumber(),currentSemester);
            TodayAttendanceStatus todayAttendanceStatus = todayAttendanceStatusRepo.findByRollNumber(studentTable.getRollNumber());
            Map<String,Object> studentDetail = new HashMap<>();
            studentDetail.put("Name",studentTable.getName());
            studentDetail.put("Roll no",studentTable.getRollNumber());
            studentDetail.put("Leave Status", todayAttendanceStatus.getLeaveStatus());
            studentDetail.put("Approved status",todayAttendanceStatus.isLeaveApproval());
            studentDetail.put("OnDuty" , todayAttendanceStatus.isOdleave());
            studentDetail.put("percentage",studentAttendance.getAttendancePercentage());
            result.add(studentDetail);
            if(todayAttendanceStatus.getLeaveStatus())
            {
                absent++;
                if(todayAttendanceStatus.isLeaveApproval())
                {
                    approvedLeave++;
                }
            }
            if(todayAttendanceStatus.isOdleave())
            {
                onDuty++;
            }
            percentage+=studentAttendance.getAttendancePercentage();
        }
        DailyDepartmentReport dailyDepartmentReport = new DailyDepartmentReport();
        dailyDepartmentReport.setClassId(classId);
        dailyDepartmentReport.setNoOfApprovedLeave(approvedLeave);
        dailyDepartmentReport.setPercentage(percentage/studentCount);
        dailyDepartmentReport.setNoOfOd(onDuty);
        dailyDepartmentReport.setNoOfStudentPresent(studentCount-(onDuty+absent));
        dailyDepartmentReport.setTotalNumberOfStudent(studentCount);
        dailyDepartmentReportRepo.save(dailyDepartmentReport);
        creatingJsonFile(classId, dir, jsonObject, result,"class");
    }
    public void writeReportForDepartment(Long departId)
    {
        String dir = baseDir+ File.separator+departId;
        JSONObject jsonObject = new JSONObject();
        List<Map<String,Object>> result = new ArrayList<>();
        List<ClassTable> classTableList =  classTableRepo.findAllByDepartmentId(departId);
        for(ClassTable classTable : classTableList) {
            DailyDepartmentReport dailyDepartmentReport = dailyDepartmentReportRepo.findDailyDepartmentReportByClassId(classTable.getId());
            Map<String, Object> classDetails = new HashMap<>();
            classDetails.put("Batch",classTable.getBatch());
            classDetails.put("Semester",(classTable.getCompletedSemester()+1));
            classDetails.put("Total Number of Student", classTable.getTotalNoOfStudents());
            classDetails.put("Number of Absentees",dailyDepartmentReport.getNoOfStudentsAbsent());
            classDetails.put("Number of Approved leaves",dailyDepartmentReport.getNoOfApprovedLeave());
            classDetails.put("Number of Onduty",dailyDepartmentReport.getNoOfOd());
            result.add(classDetails);
        }
        creatingJsonFile(departId, dir, jsonObject, result,"department");
    }

    private void creatingJsonFile(Long departId, String dir, JSONObject jsonObject, List<Map<String, Object>> result ,String name) {
        try {
            Date d = new Date();
            String dirPath = baseDir + File.separator+name+File.separator+departId.toString();
            writeInTheFile(departId, dir, jsonObject, result, d, dirPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
