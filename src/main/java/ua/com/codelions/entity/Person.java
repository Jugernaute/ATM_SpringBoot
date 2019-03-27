package ua.com.codelions.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Component
public class Person implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Person;
    private String username;
    private String password;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @OneToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "person")
    private CreditCard creditCard;

    public Person() {
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
//        this.creditCard = creditCard;
    }

    public long getId_Person() {
        return id_Person;
    }

    public void setId_Person(int id_Person) {
        this.id_Person = id_Person;
    }

    public String getUsername() {
        return username;
    }



    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id_Person=" + id_Person +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
