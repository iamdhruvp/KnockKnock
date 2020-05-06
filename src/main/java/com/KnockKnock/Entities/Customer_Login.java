package com.KnockKnock.Entities;

/**
 * Created on  : 06/05/20 - 7:52 PM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    :
 */
public class Customer_Login {
    private String customerName;
    private String customerGender;
    private String customerEmail;
    private String password;
    private String mobileNo;

    public Customer_Login(String customerName, String customerGender, String customerEmail, String password, String mobileNo) {
        this.customerName = customerName;
        this.customerGender = customerGender;
        this.customerEmail = customerEmail;
        this.password = password;
        this.mobileNo = mobileNo;
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
}
