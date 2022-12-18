package com.project.repository;

import com.project.Model.CancelledLeave;
import com.project.Model.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance, Long> {
    List<StudentAttendance> findAllByStudentRollNumber(String rollNo);
    StudentAttendance findStudentAttendanceByStudentRollNumberAndSemester(String rollNo, int semester);
    @Query(value = "select studentRollNumber from StudentAttendance where semester=?1 and attendancePercentage<?2")
    List<String> findBySemesterAndAttendancePercentageLessThan(int semester, double per);
}
