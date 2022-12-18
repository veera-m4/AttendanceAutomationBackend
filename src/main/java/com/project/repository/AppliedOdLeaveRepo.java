package com.project.repository;

import com.project.Model.AppliedLeave;
import com.project.Model.AppliedOdLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppliedOdLeaveRepo extends JpaRepository<AppliedOdLeave, Long> {
}
