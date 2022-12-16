package com.project.repository;

import com.project.Model.CancelledLeave;
import com.project.Model.DepartmentTable;
import org.hibernate.annotations.LazyToOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentTableRepo extends JpaRepository<DepartmentTable, Long> {
}
