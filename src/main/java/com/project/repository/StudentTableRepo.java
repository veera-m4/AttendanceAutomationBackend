package com.project.repository;

import com.project.Model.CancelledLeave;
import com.project.Model.StudentTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentTableRepo  extends JpaRepository<StudentTable, String> {
    StudentTable findStudentTableByRollNumber(String rollNo);
    List<StudentTable> findAllByClassid(int classId);
}
