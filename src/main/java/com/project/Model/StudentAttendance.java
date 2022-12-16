package com.project.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class StudentAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String studentRollNumber;
    private Integer semester;
    private Integer totalNumberOfWorking=0;
    private Integer noOfApprovedLeaves=0;
    private Integer totalNumberLeaves=0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentRollNumber() {
        return studentRollNumber;
    }

    public void setStudentRollNumber(String studentRollNumber) {
        this.studentRollNumber = studentRollNumber;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getTotalNumberOfWorking() {
        return totalNumberOfWorking;
    }

    public void setTotalNumberOfWorking(Integer totalNumberOfWorking) {
        this.totalNumberOfWorking = totalNumberOfWorking;
    }

    public Integer getNoOfApprovedLeaves() {
        return noOfApprovedLeaves;
    }

    public void setNoOfApprovedLeaves(Integer noOfApprovedLeaves) {
        this.noOfApprovedLeaves = noOfApprovedLeaves;
    }

    public Integer getTotalNumberLeaves() {
        return totalNumberLeaves;
    }

    public void setTotalNumberLeaves(Integer totalNumberLeaves) {
        this.totalNumberLeaves = totalNumberLeaves;
    }
}
