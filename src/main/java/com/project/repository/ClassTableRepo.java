package com.project.repository;

import com.project.Model.CancelledLeave;
import com.project.Model.ClassTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassTableRepo extends JpaRepository<ClassTable, Long> {
    ClassTable findClassTableById(Long id);
    List<ClassTable> findAllByDepartmentId(Long depId);
}
