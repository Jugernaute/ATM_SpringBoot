package ua.com.codelions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.codelions.dto.PersonDTO;
import ua.com.codelions.entity.CreditCard;
import ua.com.codelions.entity.Person;
import ua.com.codelions.service.creditCard.CreditCardService;
import ua.com.codelions.service.persone.PersonService;

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
        CreditCard creditCard = new CreditCard();
        creditCard.setPerson(person);
        creditCardService.saveCreditCard(creditCard);
        personService.savePersone(person);
//        Thread.currentThread().setUncaughtExceptionHandler((t, e) -> System.out.println(t + " throws exception: " + e));
        return "login";
    }

    @PostMapping("/successURL")
    public String successURL(Model model) {
        PersonDTO personDTO = new PersonDTO(personService);
        List<CreditCard> all = creditCardService.findAll();
        model.addAttribute("personDTO", personDTO);
        model.addAttribute("cards", all);
        return "index";
}

    @GetMapping("/login-error")
        private String error (){
        return "login-error";
    }
}
