package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelledLeaveRepo extends JpaRepository<com.project.Model.CancelledLeave, Long> {
}
