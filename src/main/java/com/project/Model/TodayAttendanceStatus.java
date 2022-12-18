package com.project.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Entity
@DynamicUpdate
public class TodayAttendanceStatus {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roll_number", nullable = false)
    private String rollNumber;
    private Boolean leaveStatus = null;
    private boolean leaveApproval = false;
    private boolean odleave = false;

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Boolean getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(Boolean leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public boolean isLeaveApproval() {
        return leaveApproval;
    }

    public void setLeaveApproval(boolean leaveApproval) {
        this.leaveApproval = leaveApproval;
    }

    public boolean isOdleave() {
        return odleave;
    }

    public void setOdleave(boolean odleave) {
        this.odleave = odleave;
    }
}
