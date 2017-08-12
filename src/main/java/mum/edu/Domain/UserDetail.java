package mum.edu.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
public class UserDetail {
    @Id
    @GeneratedValue
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean active;
    @OneToMany
    @JoinColumn(name = "addressId")
    private List<Address> addresses = new ArrayList<>();

    public UserDetail() {
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
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
