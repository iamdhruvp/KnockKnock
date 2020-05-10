package com.KnockKnock.Entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column
    @Size(max=50)
    @NotNull
    private String customerName;

    @Column
    @Size(max=20)
    @NotNull
    private String customerGender;

    @Column(unique = true)
    @Size(max=100)
    @NotNull
    @Pattern(regexp = "(^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$)")
    private String customerEmail;


    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Login login;




    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date customerBirthDate=new Date(2323223232L);

    @OneToOne(cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    @Column
//    @NotNull
    @Lob
    private Byte[] customerPhoto;

    public Customer(){

    }

    public Customer(@Size(max = 50) @NotNull String customerName, @Size(max = 20) @NotNull String customerGender, @Size(max = 100) @NotNull @Pattern(regexp = "(^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$)") String customerEmail) {
        this.customerName = customerName;
        this.customerGender = customerGender;
        this.customerEmail = customerEmail;
    }

    public Customer(@Size(max = 50) @NotNull String customerName, @Size(max = 20) @NotNull String customerGender, @Size(max = 100) @NotNull @Pattern(regexp = "(^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$)") String customerEmail, @NotNull Login login) {
        this.customerName = customerName;
        this.customerGender = customerGender;
        this.customerEmail = customerEmail;
        this.login = login;
    }

    public Customer(@Size(max = 50) @NotNull String customerName, @Size(max = 20) @NotNull String customerGender, @Size(max = 100) @NotNull @Pattern(regexp = "(^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$)") String customerEmail, @NotNull Byte[] customerPhoto) {
        this.customerName = customerName;
        this.customerGender = customerGender;
        this.customerEmail = customerEmail;
        this.customerPhoto = customerPhoto;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public Byte[] getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(Byte[] customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }


    public Date getCustomerBirthDate() {
        return customerBirthDate;
    }

    public void setCustomerBirthDate(Date customerBirthDate) {
        this.customerBirthDate = customerBirthDate;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
