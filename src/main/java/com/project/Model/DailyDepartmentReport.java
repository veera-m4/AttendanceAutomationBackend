package com.project.Model;


import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class DailyDepartmentReport {
    @Id
    @Column(name = "class_id", nullable = false)
    private Long classId;
    private Integer totalNumberOfStudent;
    private Integer noOfStudentPresent;
    private Integer noOfStudentsAbsent;
    private Integer noOfApprovedLeave;
    private Integer noOfOd;
    private Double percentage;


    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getTotalNumberOfStudent() {
        return totalNumberOfStudent;
    }

    public void setTotalNumberOfStudent(Integer totalNumberOfStudent) {
        this.totalNumberOfStudent = totalNumberOfStudent;
    }

    public Integer getNoOfStudentPresent() {
        return noOfStudentPresent;
    }

    public void setNoOfStudentPresent(Integer noOfStudentPresent) {
        this.noOfStudentPresent = noOfStudentPresent;
    }

    public Integer getNoOfStudentsAbsent() {
        return noOfStudentsAbsent;
    }

    public void setNoOfStudentsAbsent(Integer noOfStudentsAbsent) {
        this.noOfStudentsAbsent = noOfStudentsAbsent;
    }

    public Integer getNoOfApprovedLeave() {
        return noOfApprovedLeave;
    }

    public void setNoOfApprovedLeave(Integer noOfApprovedLeave) {
        this.noOfApprovedLeave = noOfApprovedLeave;
    }

    public Integer getNoOfOd() {
        return noOfOd;
    }

    public void setNoOfOd(Integer noOfOd) {
        this.noOfOd = noOfOd;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
