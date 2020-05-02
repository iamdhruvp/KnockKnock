package com.KnockKnock.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created on  : 03/05/20 - 2:23 AM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    :
 */
@Entity
@Table(name="BankAccount")
public class BankAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bankAccountId;

    @Column(unique = true)
    @Size(max=50)
    @NotNull
    private String bankAccountNo;

    @Column
    @Size(max=50)
    @NotNull
    private String bankAccountName;

    @Column
    @Size(max=20)
    @NotNull
    private String bankIFCI;
}
