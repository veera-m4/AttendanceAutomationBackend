package com.project.service;


import com.project.repository.AppliedLeaveRepo;
import com.project.Model.ClassTable;
import com.project.Model.StudentAttendance;
import com.project.Model.StudentTable;
import com.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Component
public class StudentService {
    @Autowired
    AppliedLeaveRepo appliedLeaveRepo;
    @Autowired
    ApprovedLeaveRepo approvedLeaveRepo;
    @Autowired
    ApprovedLeavebyTeacherRepo approvedLeavebyTeacherRepo;
    @Autowired
    CancelledLeaveRepo cancelledLeaveRepo;
    @Autowired
    ClassTableRepo classTableRepo;
    @Autowired
    DepartmentTableRepo departmentTableRepo;
    @Autowired
    FacultyTableRepo facultyTableRepo;
    @Autowired
    StudentAttendanceRepo studentAttendanceRepo;
    @Autowired
    StudentTableRepo studentTableRepo;
    public Map<String, Object> studentDetails(String studentRollNumber)
    {
        Map<String, Object> result = new HashMap<>();
        StudentTable studentTable = studentTableRepo.findStudentTableByRollNumber(studentRollNumber);
        ClassTable classTable = classTableRepo.findClassTableById(studentTable.getClassid());
        int completedSemester = classTable.getCompletedSemester();
        completedSemester++;
        Map<Integer, Map<String,Object>> attendanceDataBYSemWise = new HashMap<>();
        for(int i=1;i<=completedSemester;i++)
        {
            StudentAttendance studentAttendance = studentAttendanceRepo.findStudentAttendanceByStudentRollNumberAndSemester(studentRollNumber,i);
            Map<String,Object> attendancePercentagebySem = new HashMap<>();
            attendancePercentagebySem.put("Total Number Working Days",studentAttendance.getTotalNumberOfWorking());
            attendancePercentagebySem.put("Total Number Leaves",studentAttendance.getTotalNumberLeaves());
            attendancePercentagebySem.put("Number Approved Leaves",studentAttendance.getNoOfApprovedLeaves());
            attendancePercentagebySem.put("Percentage",(double)studentAttendance.getTotalNumberOfWorking()/studentAttendance.getTotalNumberLeaves()*100);
            attendanceDataBYSemWise.put(i,attendancePercentagebySem);
        }
        result.put("Name",studentTable.getName());
        result.put("Roll Number",studentTable.getRollNUmber());
        result.put("Date of Birth",studentTable.getDateOfBirth());
        result.put("Attendance by semester wise",attendanceDataBYSemWise);
        return result;
    }
}
