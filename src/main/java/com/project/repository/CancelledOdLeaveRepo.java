package com.project.repository;

import com.project.Model.ApprovedOdleave;
import com.project.Model.CancelledOdLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CancelledOdLeaveRepo extends JpaRepository<CancelledOdLeave, Long> {
}