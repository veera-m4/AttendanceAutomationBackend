package com.project.repository;

import com.project.Model.AppliedLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AppliedLeaveRepo extends JpaRepository<AppliedLeave, Long> {
}


