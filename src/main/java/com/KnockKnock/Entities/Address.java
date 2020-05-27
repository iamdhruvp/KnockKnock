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
    private String addressLandmark;

    @Column
    @Size(max=6)
    @NotNull
    private String addressPincode;


    @OneToOne
    private City addressCity;

    @Column
    @NotNull
    private Integer isDefaultAddress;

    public Address() {
    }

    public Address(@Size(max = 50) @NotNull String addressName, @Size(max = 200) @NotNull String addressLine, @Size(max = 200) @NotNull String addressLandmark, @Size(max = 6) @NotNull String addressPincode, City addressCity, @NotNull Integer isDefaultAddress) {
        this.addressName = addressName;
        this.addressLine = addressLine;
        this.addressLandmark = addressLandmark;
        this.addressPincode = addressPincode;
        this.addressCity = addressCity;
        this.isDefaultAddress = isDefaultAddress;
    }

    public Address(@Size(max = 50) @NotNull String addressName, @Size(max = 200) @NotNull String addressLine, @Size(max = 200) String addressLandmark, @Size(max = 6) @NotNull String addressPincode, @NotNull Integer isDefaultAddress) {
        this.addressName = addressName;
        this.addressLine = addressLine;
        this.addressLandmark = addressLandmark;
        this.addressPincode = addressPincode;
        this.isDefaultAddress = isDefaultAddress;
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

    public Integer getIsDefaultAddress() {
        return isDefaultAddress;
    }

    public void setIsDefaultAddress(Integer isDefaultAddress) {
        this.isDefaultAddress = isDefaultAddress;
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


}
