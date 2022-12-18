package com.project.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class ClassTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer batch;
    private Integer completedSemester;
    private Integer totalNoOfSemester;
    private String tutorId;
    private Integer departmentId;
    private Integer totalNoOfStudents;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public Integer getCompletedSemester() {
        return completedSemester;
    }

    public void setCompletedSemester(Integer completedSemester) {
        this.completedSemester = completedSemester;
    }

    public Integer getTotalNoOfSemester() {
        return totalNoOfSemester;
    }

    public void setTotalNoOfSemester(Integer totalNoOfSemester) {
        this.totalNoOfSemester = totalNoOfSemester;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getTotalNoOfStudents() {
        return totalNoOfStudents;
    }

    public void setTotalNoOfStudents(Integer totalNoOfStudents) {
        this.totalNoOfStudents = totalNoOfStudents;
    }
}
