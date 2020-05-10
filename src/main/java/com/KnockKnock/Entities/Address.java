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
@Table(name="Address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column
    @Size(max=50)
    @NotNull
    private String addressName;

    @Column
    @Size(max=200)
    @NotNull
    private String addressLine;

    @Column
    @Size(max=200)
    @NotNull
    private String addressLandmark;

    @Column
    @Size(max=6)
    @NotNull
    private String addressPincode;


    @ManyToOne(fetch = FetchType.EAGER)
    private City addressCity;

    @Column
    @NotNull
    private Boolean isDefaultAddress;


    @ManyToOne
    private Customer customer;

    public Address() {
    }

    public Address(@Size(max = 50) @NotNull String addressName, @Size(max = 200) @NotNull String addressLine, @Size(max = 200) @NotNull String addressLandmark, @Size(max = 6) @NotNull String addressPincode, @NotNull City addressCity, @NotNull Boolean isDefaultAddress, @NotNull Customer customer) {
        this.addressName = addressName;
        this.addressLine = addressLine;
        this.addressLandmark = addressLandmark;
        this.addressPincode = addressPincode;
        this.addressCity = addressCity;
        this.isDefaultAddress = isDefaultAddress;
        this.customer = customer;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getAddressLandmark() {
        return addressLandmark;
    }

    public City getAddressCity() {
        return addressCity;
    }

    public Boolean getDefaultAddress() {
        return isDefaultAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getAddressPincode() {
        return addressPincode;
    }

    public void setAddressPincode(String addressPincode) {
        this.addressPincode = addressPincode;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public void setAddressLandmark(String addressLandmark) {
        this.addressLandmark = addressLandmark;
    }

    public void setAddressCity(City addressCity) {
        this.addressCity = addressCity;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        isDefaultAddress = defaultAddress;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
