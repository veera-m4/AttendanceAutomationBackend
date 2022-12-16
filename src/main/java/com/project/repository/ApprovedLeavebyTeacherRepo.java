package com.project.repository;

import com.project.Model.AppliedLeave;
import com.project.Model.ApprovedLeavebyTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovedLeavebyTeacherRepo  extends JpaRepository<ApprovedLeavebyTeacher, Long> {
}
