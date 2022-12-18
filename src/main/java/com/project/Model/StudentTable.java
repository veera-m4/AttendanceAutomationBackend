package com.project.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Entity
@DynamicUpdate
public class StudentTable {
    @Id
    @Column(name = "roll_number", nullable = false)
    private String rollNumber;

    private String name;
    private Long classid;
    private Date dateOfBirth;
    private String mailId;


    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
}
