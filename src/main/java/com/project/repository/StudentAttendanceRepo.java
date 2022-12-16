package com.project.repository;

import com.project.Model.CancelledLeave;
import com.project.Model.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance, Long> {
    List<StudentAttendance> findAllByStudentRollNumber(String rollNo);
    StudentAttendance findStudentAttendanceByStudentRollNumberAndSemester(String rollNo, int semester);
}
