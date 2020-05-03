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

    public BankAccount() {
    }

    public BankAccount(@Size(max = 50) @NotNull String bankAccountNo, @Size(max = 50) @NotNull String bankAccountName, @Size(max = 20) @NotNull String bankIFCI) {
        this.bankAccountNo = bankAccountNo;
        this.bankAccountName = bankAccountName;
        this.bankIFCI = bankIFCI;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankIFCI() {
        return bankIFCI;
    }

    public void setBankIFCI(String bankIFCI) {
        this.bankIFCI = bankIFCI;
    }
}
