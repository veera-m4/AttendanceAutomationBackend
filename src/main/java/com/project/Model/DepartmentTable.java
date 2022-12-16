package com.project.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class DepartmentTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String hodId;
    private Integer totalNoOfClass;
    private Integer totalStudents;
    private Integer totalFaculties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHodId() {
        return hodId;
    }

    public void setHodId(String hodId) {
        this.hodId = hodId;
    }

    public Integer getTotalNoOfClass() {
        return totalNoOfClass;
    }

    public void setTotalNoOfClass(Integer totalNoOfClass) {
        this.totalNoOfClass = totalNoOfClass;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getTotalFaculties() {
        return totalFaculties;
    }

    public void setTotalFaculties(Integer totalFaculties) {
        this.totalFaculties = totalFaculties;
    }
}
