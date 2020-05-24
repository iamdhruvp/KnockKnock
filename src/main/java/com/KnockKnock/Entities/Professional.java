package com.KnockKnock.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
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


   // @NotNull
    @ManyToMany(targetEntity = ServiceSubCategory.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ServiceSubCategory> serviceSubCategories = new HashSet<>();


//    @ManyToMany(targetEntity = Service.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "professional_services",
//            joinColumns = { @JoinColumn(name = "professional_id") },
//            inverseJoinColumns = { @JoinColumn(name = "service_id") })
//    [Ref.]https://thoughts-on-java.org/hibernate-tip-many-to-many-association-with-additional-attributes/
  //  @NotNull
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

    private String professionalBirthDate;

    @Column
    @NotNull
    private Integer professionalExperience;


//    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


   // @NotNull
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



    public Professional(@Size(max = 50) @NotNull String professionalName, @Size(max = 20) @NotNull String professionalGender, @Size(max = 100) @NotNull @Pattern(regexp = "(^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$)") String professionalEmail, @NotNull Login login, @Size(max = 50) @NotNull String professionalGSTNo, @NotNull  String professionalBirthDate, @NotNull Integer professionalExperience) {
        this.professionalName = professionalName;
        this.professionalGender = professionalGender;
        this.professionalEmail = professionalEmail;
        this.login = login;
        this.professionalGSTNo = professionalGSTNo;
        this.professionalBirthDate = professionalBirthDate;
        this.professionalExperience = professionalExperience;
    }

    public Professional() {
    }
}
