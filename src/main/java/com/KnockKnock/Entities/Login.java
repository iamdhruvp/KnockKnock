package com.KnockKnock.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created on  : 03/05/20 - 1:03 AM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    :
 */
@Entity
@Table(name="Login",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"mobileNo", "userRoleId"}))
public class Login implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginId;

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate=new Date(2323223232L);

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate=new Date(2323223232L);

    @Column
    @NotNull
    @Pattern(regexp="(^$|[0-9]{10})")
    private String mobileNo;

    @Column
    @NotNull
    @Size(min=6, max = 32)
    @Pattern(regexp="(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$)")
    private String password;

    @Column
    @Size(min=6, max = 32)
    @Pattern(regexp="(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$)")
    private String oldPassword1;

    @Column
    @Size(min=6, max = 32)
    @Pattern(regexp="(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$)")
    private String oldPassword2;

    @Column
    @NotNull
    private Character status;


    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userRoleId")
    private UserRole userRole;

    public Login() {
    }

    public Login(@NotNull @Pattern(regexp = "(^$|[0-9]{10})") String mobileNo, @NotNull @Size(min = 6, max = 32) @Pattern(regexp = "(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$)") String password) {
        this.mobileNo = mobileNo;
        this.password = password;
    }

    public Login(@NotNull Date registerDate, @NotNull Date lastLoginDate, @NotNull @Pattern(regexp = "(^$|[0-9]{10})") String mobileNo, @NotNull @Size(min = 6, max = 32) @Pattern(regexp = "(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$)") String password, @Size(min = 6, max = 32) @Pattern(regexp = "(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$)") String oldPassword1, @Size(min = 6, max = 32) @Pattern(regexp = "(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$)") String oldPassword2, @NotNull Character status, @NotNull UserRole userRole) {
        this.registerDate = registerDate;
        this.lastLoginDate = lastLoginDate;
        this.mobileNo = mobileNo;
        this.password = password;
        this.oldPassword1 = oldPassword1;
        this.oldPassword2 = oldPassword2;
        this.status = status;
        this.userRole = userRole;
    }

    public Login(@NotNull Date registerDate, @NotNull Date lastLoginDate, @NotNull @Pattern(regexp = "(^$|[0-9]{10})") String mobileNo, @NotNull @Size(min = 6, max = 32) @Pattern(regexp = "(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$)") String password, @NotNull Character status, @NotNull UserRole userRole) {
        this.registerDate = registerDate;
        this.lastLoginDate = lastLoginDate;
        this.mobileNo = mobileNo;
        this.password = password;
        this.status = status;
        this.userRole = userRole;
    }

    public Login(Date date, Date date1, String mobile, String password, char a, int i) {
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword1() {
        return oldPassword1;
    }

    public void setOldPassword1(String oldPassword1) {
        this.oldPassword1 = oldPassword1;
    }

    public String getOldPassword2() {
        return oldPassword2;
    }

    public void setOldPassword2(String oldPassword2) {
        this.oldPassword2 = oldPassword2;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
