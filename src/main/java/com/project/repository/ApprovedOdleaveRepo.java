package com.project.repository;


import com.project.Model.AppliedOdLeave;
import com.project.Model.ApprovedOdleave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ApprovedOdleaveRepo extends JpaRepository<ApprovedOdleave, Long> {
    ApprovedOdleave findApprovedOdleaveByDateAndAndStudentId(Date date, String studentId);
    List<ApprovedOdleave> findAllByDate(Date date);
}
