package ua.com.codelions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.codelions.dao.CreditCardDao;
import ua.com.codelions.entity.Bills;
import ua.com.codelions.entity.CreditCard;
import ua.com.codelions.entity.Person;
import ua.com.codelions.service.creditCard.CreditCardService;
import ua.com.codelions.service.persone.PersonService;

import javax.naming.Context;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    PersonService personService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CreditCardService creditCardService;

    @GetMapping("/")
    private String start (){
        return "login";
    }

    @PostMapping("/save")
    public String savePerson(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        CreditCard creditCard = new CreditCard(0);
        creditCard.setPerson(person);
        creditCardService.saveCreditCard(creditCard);
        personService.savePersone(person);
        return "login";
    }

    @PostMapping("/successURL")
    public String successURL(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails userDetails = personService.loadUserByUsername(name);
        List<Integer> bills = new ArrayList<>();
        for (Bills f : Bills.values()) {
            bills.add(f.index());
        }
        List<CreditCard> all = creditCardService.findAll();
        model.addAttribute("bills", bills);
        model.addAttribute("user", userDetails);
        model.addAttribute("cards", all);
        return "index";
    }

    @GetMapping("/login-error")
        private String error (){
        return "login-error";
    }
}
