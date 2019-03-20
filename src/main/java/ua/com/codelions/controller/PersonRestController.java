package ua.com.codelions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.codelions.methods.Methods;
import ua.com.codelions.service.persone.PersonService;

import java.util.Map;

@RestController
public class PersonRestController {
    @Autowired
    PersonService personService;
    @Autowired
    Methods methods;

    @GetMapping("/selfRefill/")
    private Map selfRefill(@RequestParam int sum){
       return methods.plusMoneyIfTrueAndMinusIfFalse(sum, true);
    }

    @GetMapping("/getMoney/")
    private Map getMoney(@RequestParam int sum){
        return methods.plusMoneyIfTrueAndMinusIfFalse(sum, false);
    }

    @GetMapping("/transferMoney/")
    private Map transferMoney(@RequestParam int money,
                              @RequestParam int idCard){
        return methods.moneyFromOnePersonToAnother(money, idCard);
    }
}
