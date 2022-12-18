package com.project.repository;

import com.project.Model.DailyDepartmentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DailyDepartmentReportRepo extends JpaRepository<DailyDepartmentReport, Long> {
    DailyDepartmentReport findDailyDepartmentReportByClassId(Long classId);
}