package ua.com.codelions.entity;

import javax.persistence.*;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_CreditCard;
    private int money;

    @OneToOne(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    private Person person;

    public CreditCard() {
    }

    public CreditCard(int money) {
        this.money = money;
    }

    public int getId_CreditCard() {
        return id_CreditCard;
    }

    public void setId_CreditCard(int id_CreditCard) {
        this.id_CreditCard = id_CreditCard;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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
