package com.project.service;

import com.project.Model.StudentTable;
import com.project.Model.TodayAttendanceStatus;
import com.project.repository.StudentTableRepo;
import com.project.repository.TodayAttendanceStatusRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
@Service
public class ClassService {
    @Autowired
    TodayAttendanceStatusRepo todayAttendanceStatusRepo;
    @Autowired
    StudentTableRepo studentTableRepo;
    public void writeReportForClass(int classId)
    {
        JSONObject jsonObject = new JSONObject();
        List<StudentTable> studentTableList = studentTableRepo.findAllByClassid(classId);
        TreeMap<String , Map<String, Object>> result = new TreeMap<>();
        for(StudentTable studentTable : studentTableList)
        {
            
        }
    }

}
