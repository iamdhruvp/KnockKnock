package com.KnockKnock.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created on  : 03/05/20 - 12:18 AM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    :
 */
@Entity
@Table(name = "UserRole")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    @Column(unique = true)
    @Size(max=50)
    @NotNull
    private String userRoleName;

    public UserRole() {
    }

    public UserRole(@Size(max = 50) @NotNull String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public UserRole(Long userRoleId, @Size(max = 50) @NotNull String userRoleName) {
        this.userRoleId=userRoleId;
        this.userRoleName = userRoleName;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
}
