package com.project.repository;

import com.project.Model.ApprovedLeave;
import com.project.Model.ApprovedLeavebyTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ApprovedLeaveRepo  extends JpaRepository<ApprovedLeave,Long> {
}
