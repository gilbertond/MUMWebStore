package mum.edu.Domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Email;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
@Table(name = "userdetail")
public class UserDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "userDetail", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId")
    private List<Address> addresses = new ArrayList<>();
    @ElementCollection
    @Column(name = "userrole")
    @Enumerated(EnumType.STRING)
    List<Role> roles;
    
    public UserDetail() {
        this.addresses = new ArrayList<>();
    }

    public UserDetail(String firstName, String lastName, String email, String password, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.addresses = new ArrayList<>();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    private int addressSize() {
        return this.addresses.size();
    }

    public void addAddress(Address address) {
        this.addresses.add(addressSize(), address);
    }

    public void addAddress(List<Address> addresses) {
        this.addresses.addAll(addressSize(), addresses);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + Objects.hashCode(this.userId);
        hash = 31 * hash + Objects.hashCode(this.firstName);
        hash = 31 * hash + Objects.hashCode(this.lastName);
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
        final UserDetail other = (UserDetail) obj;
        if (!Objects.equals(this.userId, other.getUserId())) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.getFirstName())) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.getLastName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserDetail{" + "userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", active=" + active + ", addresses=" + addresses.size() + '}';
    }
}
