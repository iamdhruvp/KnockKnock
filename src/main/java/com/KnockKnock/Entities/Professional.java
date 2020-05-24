package com.KnockKnock.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on  : 03/05/20 - 3:17 AM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    : Read below article to work with photos in database
 * https://stackoverflow.com/questions/50363639/how-spring-boot-jpahibernate-saves-images
 */
@Entity
@Table(name="Professional")
public class Professional implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professionalId;

    @Column
    @Size(max=50)
    @NotNull
    private String professionalName;

    @Column
    @Size(max=20)
    @NotNull
    private String professionalGender;

    @Column(unique = true)
    @Size(max=100)
    @NotNull
    @Pattern(regexp = "(^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$)")
    private String professionalEmail;


    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Login login;


    @NotNull
    @ManyToMany(targetEntity = ServiceSubCategory.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ServiceSubCategory> serviceSubCategories = new HashSet<>();


//    @ManyToMany(targetEntity = Service.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "professional_services",
//            joinColumns = { @JoinColumn(name = "professional_id") },
//            inverseJoinColumns = { @JoinColumn(name = "service_id") })
//    [Ref.]https://thoughts-on-java.org/hibernate-tip-many-to-many-association-with-additional-attributes/
    @NotNull
    @OneToMany(mappedBy = "service")
    private Set<ProfessionalService> professionalServices = new HashSet<>();

    @Column
    @Size(max=50)
    @NotNull
    private String professionalGSTNo;


    @Column
//    @NotNull
    @Lob
    private Byte[] professionalGovtDoc;

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date professionalBirthDate=new Date(2323223232L);

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date professionalExperience;


//    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


    @NotNull
    @OneToOne
    private City servingCity;


//    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    @Column
//    @NotNull
    @Lob
    private Byte[] customerPhoto;

    public Long getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(Long professionalId) {
        this.professionalId = professionalId;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public String getProfessionalGender() {
        return professionalGender;
    }

    public void setProfessionalGender(String professionalGender) {
        this.professionalGender = professionalGender;
    }

    public String getProfessionalEmail() {
        return professionalEmail;
    }

    public void setProfessionalEmail(String professionalEmail) {
        this.professionalEmail = professionalEmail;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Set<ServiceSubCategory> getServiceSubCategories() {
        return serviceSubCategories;
    }

    public void setServiceSubCategories(Set<ServiceSubCategory> serviceSubCategories) {
        this.serviceSubCategories = serviceSubCategories;
    }

    public Set<ProfessionalService> getProfessionalServices() {
        return professionalServices;
    }

    public void setProfessionalServices(Set<ProfessionalService> professionalServices) {
        this.professionalServices = professionalServices;
    }

    public String getProfessionalGSTNo() {
        return professionalGSTNo;
    }

    public void setProfessionalGSTNo(String professionalGSTNo) {
        this.professionalGSTNo = professionalGSTNo;
    }

    public Byte[] getProfessionalGovtDoc() {
        return professionalGovtDoc;
    }

    public void setProfessionalGovtDoc(Byte[] professionalGovtDoc) {
        this.professionalGovtDoc = professionalGovtDoc;
    }

    public Date getProfessionalBirthDate() {
        return professionalBirthDate;
    }

    public void setProfessionalBirthDate(Date professionalBirthDate) {
        this.professionalBirthDate = professionalBirthDate;
    }

    public Date getProfessionalExperience() {
        return professionalExperience;
    }

    public void setProfessionalExperience(Date professionalExperience) {
        this.professionalExperience = professionalExperience;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public City getServingCity() {
        return servingCity;
    }

    public void setServingCity(City servingCity) {
        this.servingCity = servingCity;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Byte[] getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(Byte[] customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public Professional(@Size(max = 50) @NotNull String professionalName, @Size(max = 20) @NotNull String professionalGender, @Size(max = 100) @NotNull @Pattern(regexp = "(^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$)") String professionalEmail, @NotNull Login login, @NotNull Set<ServiceSubCategory> serviceSubCategories, @NotNull Set<ProfessionalService> professionalServices, @Size(max = 50) @NotNull String professionalGSTNo, @NotNull Byte[] professionalGovtDoc, @NotNull Date professionalBirthDate, @NotNull Date professionalExperience, @NotNull Address address, @NotNull City servingCity, @NotNull BankAccount bankAccount, @NotNull Byte[] customerPhoto) {
        this.professionalName = professionalName;
        this.professionalGender = professionalGender;
        this.professionalEmail = professionalEmail;
        this.login = login;
        this.serviceSubCategories = serviceSubCategories;
        this.professionalServices = professionalServices;
        this.professionalGSTNo = professionalGSTNo;
        this.professionalGovtDoc = professionalGovtDoc;
        this.professionalBirthDate = professionalBirthDate;
        this.professionalExperience = professionalExperience;
        this.address = address;
        this.servingCity = servingCity;
        this.bankAccount = bankAccount;
        this.customerPhoto = customerPhoto;
    }

    public Professional() {
    }
}
