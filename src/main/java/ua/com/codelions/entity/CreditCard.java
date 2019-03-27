package ua.com.codelions.entity;

import org.springframework.format.number.money.MonetaryAmountFormatter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_CreditCard;

    @NotNull
    private BigDecimal money = BigDecimal.ZERO;

    @OneToOne(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    private Person person;

    public CreditCard() {
    }

    public CreditCard(BigDecimal money, Person person) {
        this.money = money;
        this.person = person;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public long getId_CreditCard() {
        return id_CreditCard;
    }

    public void setId_CreditCard(int id_CreditCard) {
        this.id_CreditCard = id_CreditCard;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id_CreditCard=" + id_CreditCard +
                ", money=" + money +
                '}';
    }
}
