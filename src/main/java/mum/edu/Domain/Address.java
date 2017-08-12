package mum.edu.Domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "stateUS")
    private String stateUS;
    @Column(name = "country")
    private String country;
    @Column(name = "zipCode")
    private String zipCode;
    @ManyToOne
    @Column(name = "userdetail")
    private UserDetail userDetail;

    public Address() {
    }

    public Address(String street, String city, String state, String country, String zipCode, UserDetail userDetail) {
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
