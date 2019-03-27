package ua.com.codelions.dto;

import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.codelions.entity.Person;
import ua.com.codelions.service.persone.PersonService;

import java.math.BigDecimal;

public class PersonDTO {

    private String username;
    private BigDecimal money;


    public PersonDTO(PersonService personService) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Person authoritiesPerson = personService.findByUsername(name);
        this.username = authoritiesPerson.getUsername();
        this.money = authoritiesPerson.getCreditCard().getMoney();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
