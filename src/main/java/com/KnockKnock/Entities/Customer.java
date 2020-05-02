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
 * Created on  : 03/05/20 - 2:39 AM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    : Read below article to work with photos in database
 *  * https://stackoverflow.com/questions/50363639/how-spring-boot-jpahibernate-saves-images
 */
@Entity
@Table(name="Customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

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

    @Column
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Login login;

    @Column
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Customer")
    private Set<Address> addresses = new HashSet<>();

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date customerBirthDate;

    @Column
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    @Column
    @NotNull
    @Lob
    private Byte[] customerPhoto;

}
