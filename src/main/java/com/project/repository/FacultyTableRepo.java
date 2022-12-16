package com.project.repository;

import com.project.Model.CancelledLeave;
import com.project.Model.FacultyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyTableRepo extends JpaRepository<FacultyTable, String> {
}
