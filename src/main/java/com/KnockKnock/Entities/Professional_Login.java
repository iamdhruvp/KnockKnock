package com.KnockKnock.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class Professional_Login {
    private String customerName;
    private String customerGender;
    private String customerEmail;
    private String password;
    private String mobileNo;
    private String professionalGSTNo;

    private String professionalBirthDate;
    private Integer professionalExperience;

    public Professional_Login(String customerName, String customerGender, String customerEmail, String password, String mobileNo, String professionalGSTNo, String professionalBirthDate, Integer professionalExperience) {
        this.customerName = customerName;
        this.customerGender = customerGender;
        this.customerEmail = customerEmail;
        this.password = password;
        this.mobileNo = mobileNo;
        this.professionalGSTNo = professionalGSTNo;
        this.professionalBirthDate = professionalBirthDate;
        this.professionalExperience = professionalExperience;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getProfessionalGSTNo() {
        return professionalGSTNo;
    }

    public void setProfessionalGSTNo(String professionalGSTNo) {
        this.professionalGSTNo = professionalGSTNo;
    }

    public String getProfessionalBirthDate() {
        return professionalBirthDate;
    }

    public void setProfessionalBirthDate(String professionalBirthDate) {
        this.professionalBirthDate = professionalBirthDate;
    }

    public Integer getProfessionalExperience() {
        return professionalExperience;
    }

    public void setProfessionalExperience(Integer professionalExperience) {
        this.professionalExperience = professionalExperience;
    }
}
