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
    private Integer professionalId;

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

    @Column
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Login login;

    @Column
    @NotNull
    @ManyToMany(targetEntity = ServiceSubCategory.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ServiceSubCategory> serviceSubCategories = new HashSet<>();

    @Column
    @NotNull
    @ManyToMany(targetEntity = Service.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Service> services = new HashSet<>();

    @Column
    @Size(max=50)
    @NotNull
    private String professionalGSTNo;


    @Column
    @NotNull
    @Lob
    private Byte[] professionalGovtDoc;

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date professionalBirthDate;

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date professionalExperience;

    @Column
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column
    @NotNull
    @OneToOne
    private City servingCity;

    @Column
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    @Column
    @NotNull
    @Lob
    private Byte[] customerPhoto;




}
