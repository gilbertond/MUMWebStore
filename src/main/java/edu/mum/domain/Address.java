package edu.mum.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @NotEmpty(message = "Street field empty")
    @Column(name = "street")
    private String street;
    @NotEmpty(message = "City field empty")
    @Column(name = "city")
    private String city;
    @NotEmpty(message = "State field empty")
    @Column(name = "stateUS")
    private String stateUS;
    @NotEmpty(message = "Country field empty")
    @Column(name = "country")
    private String country;
    @NotEmpty(message = "ZipCode field empty")
    @Pattern(regexp = "/^\\d{5,}$/", message = "At least 5 digits needed")
    @Column(name = "zipCode")
    private String zipCode;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private UserDetail userDetail;

    public Address() {
    }

    Address(String street, String city, String state, String country, String zipCode, UserDetail userDetail) {
        this.street = street;
        this.city = city;
        this.stateUS = state;
        this.country = country;
        this.zipCode = zipCode;
        this.userDetail = userDetail;
    }

    public String getStateUS() {
        return stateUS;
    }

    public void setStateUS(String stateUS) {
        this.stateUS = stateUS;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + Objects.hashCode(this.addressId);
        hash = 31 * hash + Objects.hashCode(this.street);
        hash = 31 * hash + Objects.hashCode(this.city);
        hash = 31 * hash + Objects.hashCode(this.stateUS);
        hash = 31 * hash + Objects.hashCode(this.country);
        hash = 31 * hash + Objects.hashCode(this.zipCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.addressId, other.getAddressId())) {
            return false;
        }
        if (!Objects.equals(this.street, other.getStreet())) {
            return false;
        }
        if (!Objects.equals(this.city, other.getCity())) {
            return false;
        }
        if (!Objects.equals(this.stateUS, other.getStateUS())) {
            return false;
        }
        if (!Objects.equals(this.country, other.getCountry())) {
            return false;
        }
        if (!Objects.equals(this.zipCode, other.getZipCode())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "addressId=" + addressId + ", street=" + street + ", city=" + city + ", stateUS=" + stateUS + ", country=" + country + ", zipCode=" + zipCode + '}';
    }
}
