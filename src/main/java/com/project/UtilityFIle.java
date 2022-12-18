package com.project;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UtilityFIle {
    public static List<Map<String, Object>> getJsonFileAsList(Long deptId, Date d, String baseDir) throws FileNotFoundException, ParseException {
        String dir = baseDir + File.separator+deptId;
        String fileName = dir + File.separator+d.getDate()+"-"+d.getMonth()+"-"+d.getYear()+".json";
        File file = new File(fileName);
        boolean fileExitCheck = !file.exists();
        if(fileExitCheck)
        {
            return new ArrayList<>();
        }
        JSONParser parser = new JSONParser(new FileReader(fileName));
        Map<String,List<Map<String, Object>>> Json = (Map<String,List<Map<String, Object>>>) parser.parse();
        return Json.get(deptId.toString());
    }
    public static void writeInTheFile(Long classId, String dir, JSONObject jsonObject, List<Map<String, Object>> result, Date d, String dirPath) throws JSONException, IOException {
        File filePath = new File(dirPath);
        jsonObject.put(classId.toString(),result);
        if(!filePath.exists())
        {
            filePath.mkdir();
        }
        String fileName = dir + File.separator+d.getDate()+"-"+d.getMonth()+"-"+d.getYear()+".json";
        FileWriter file = new FileWriter(fileName);
        file.write(jsonObject.toString());
        file.close();
    }
}
