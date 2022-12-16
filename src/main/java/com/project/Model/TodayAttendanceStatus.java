package com.project.Model;

import jakarta.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Entity
@DynamicUpdate
public class TodayAttendanceStatus {
    @Id
    private String rollNumber;
    private Boolean status = null;

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
