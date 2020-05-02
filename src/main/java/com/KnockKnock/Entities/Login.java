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
        @UniqueConstraint(columnNames={"mobileNo", "userRole"}))
public class Login implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loginId;

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

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

    @Column
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private UserRole userRole;

}
