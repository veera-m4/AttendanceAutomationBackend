package com.project.service;

import com.project.Model.*;
import com.project.repository.*;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

import static com.project.UtilityFIle.getJsonFileAsList;
import static com.project.UtilityFIle.writeInTheFile;


@Component
@Service
public class ClassService {
    @Autowired
    TodayAttendanceStatusRepo todayAttendanceStatusRepo;
    @Autowired
    StudentTableRepo studentTableRepo;
    @Autowired
    ClassTableRepo classTableRepo;

    @Autowired
    StudentAttendanceRepo studentAttendanceRepo;
    @Autowired
    ApprovedOdleaveRepo approvedOdleaveRepo;

    private final String baseDir = System. getProperty("user.dir");
    public void writeReportForClass(Long classId)
    {
        String dir = baseDir+File.separator+classId;
        JSONObject jsonObject = new JSONObject();
        int currentSemester = classTableRepo.findClassTableById(classId).getCompletedSemester();
        currentSemester++;
        List<StudentTable> studentTableList = studentTableRepo.findAllByClassid(classId);
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
        }
        try {
            Date d = new Date();
            String dirPath = baseDir + File.separator+classId.toString();
            writeInTheFile(classId, dir, jsonObject, result, d, dirPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public Map<String, List<Map<String, Object>>> getDailyAttendanceLeaveAndOd(Long classid) throws FileNotFoundException, ParseException {
        List<Map<String,Object>> absentList = new ArrayList<>();
        List<Map<String,Object>> below80per = new ArrayList<>();
        List<Map<String, Object>> odList = new ArrayList<>();
        List<Map<String, Object>> approvedLeaves = new ArrayList<>();
        int currentSemester = classTableRepo.findClassTableById(classid).getCompletedSemester();
        currentSemester++;
        List<String> belo80NameList = studentAttendanceRepo.findBySemesterAndAttendancePercentageLessThan(currentSemester,80.00);
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        Date d = new Date();
        String dir = baseDir+File.separator+classid;
        String fileName = dir + File.separator+d.getDate()+"-"+d.getMonth()+"-"+d.getYear()+".json";
        File file = new File(fileName);
        boolean fileExitCheck = !file.exists();
        if(fileExitCheck)
        {
            List<ApprovedOdleave> approvedOdLeaves = approvedOdleaveRepo.findAllByDate(d);
            for(ApprovedOdleave approvedOdleave : approvedOdLeaves)
            {
                String rollNo = approvedOdleave.getStudentId();
                addMapDetailsFromTable(currentSemester, rollNo,odList);
            }
            for(String rollNo : belo80NameList)
            {
                addMapDetailsFromTable(currentSemester, rollNo,below80per);
            }
        }
        else {
            JSONParser parser = new JSONParser(new FileReader(fileName));

            Map<String,List<Map<String, Object>>> Json = (Map<String,List<Map<String, Object>>>) parser.parse();
            List<Map<String, Object>> studentDetails = Json.get(classid.toString());
            for(Map<String, Object> studentTableList : studentDetails)
            {
                if((boolean) studentTableList.get("OnDuty"))
                {
                    getTemporaryObject(odList, studentTableList);
                }
                else if((boolean) studentTableList.get("Leave Status"))
                {
                    if((Boolean)studentTableList.get("Approved status"))
                    {
                        getTemporaryObject(approvedLeaves,studentTableList);
                    }
                    getTemporaryObject(absentList, studentTableList);
                }
                if(belo80NameList.contains((String) studentTableList.get("Roll no")))
                {
                    getTemporaryObject(below80per,studentTableList);
                }
            }

        }
        result.put("Absentees List",absentList);
        result.put("Onduty List", odList);
        result.put("Below 80", below80per);
        result.put("Approved Leaves",approvedLeaves);
        return result;

    }

    private void addMapDetailsFromTable(int currentSemester, String rollNo, List<Map<String, Object>> resultList) {
        StudentTable studentTable = studentTableRepo.findStudentTableByRollNumber(rollNo);
        Map<String,Object> stu = new HashMap<>();
        stu.put("Name",studentTable.getName());
        stu.put("Roll no",studentTable.getRollNumber());
        stu.put("Percentage", studentAttendanceRepo.findStudentAttendanceByStudentRollNumberAndSemester(rollNo,currentSemester));
        resultList.add(stu);
    }

    private void getTemporaryObject(List<Map<String, Object>> resultList, Map<String, Object> studentTableList) {
        Map<String,Object> stu = new HashMap<>();
        stu.put("Name",studentTableList.get("Name"));
        stu.put("Roll no",studentTableList.get("Roll no"));
        stu.put("Percentage", studentTableList.get("percentage"));
        resultList.add(stu);
    }

    public List<Map<String, Object>> getSemesterStudentAttendancePercentage(Long classId,int currentSemester)
    {
        List<Map<String, Object>> result = new ArrayList<>();

        List<StudentTable> studentList = studentTableRepo.findAllByClassid(classId);
        for(StudentTable studentTable : studentList)
        {
            Map<String, Object> studentMap =  new HashMap<>();
            StudentAttendance studentAttendance = studentAttendanceRepo.findStudentAttendanceByStudentRollNumberAndSemester(studentTable.getRollNumber(), currentSemester);
            studentMap.put("Name", studentTable.getName());
            studentMap.put("Rollno",studentTable.getRollNumber());
            studentMap.put("No of Working Days", studentAttendance.getTotalNumberOfWorking());
            studentMap.put("Total no of leaves",studentAttendance.getTotalNumberLeaves());
            studentMap.put("Approved Leaves",studentAttendance.getNoOfApprovedLeaves());
            studentMap.put("Onduty leaves",studentAttendance.getTotalNumberOdLeaves());
            result.add(studentMap);

         }
        Comparator<Map<String, Object>> cmp = new Comparator<Map<String, Object>>() {
            public int compare(Map o1, Map o2) {
                String d1 = (String) o1.get("Rollno");
                String d2 = (String) o2.get("Rollno");
                return d1.compareTo(d2);
            }
        };
        result.sort(cmp);
        return  result;
    }


    public List<Map<String, Object>> getReportByDate(Date d,Long classid) throws FileNotFoundException, ParseException {
        return getJsonFileAsList(classid, d, baseDir);
    }

}
