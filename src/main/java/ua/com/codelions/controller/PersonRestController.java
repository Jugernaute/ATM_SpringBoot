package ua.com.codelions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.codelions.service.Methods;
import ua.com.codelions.service.persone.PersonService;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class PersonRestController {
    @Autowired
    PersonService personService;
    @Autowired
    Methods methods;

    @PutMapping("/selfRefill/")
    private Map selfRefill(@RequestParam String sum){
       return methods.plusMoneyIfTrueAndMinusIfFalse(sum, true);
    }

    @PutMapping("/getMoney/")
    private Map getMoney(@RequestParam String sum){
        return methods.plusMoneyIfTrueAndMinusIfFalse(sum, false);
    }

    @PutMapping("/transferMoney/")
    private Map transferMoney(@RequestParam String money,
                              @RequestParam int idCard){
        return methods.moneyFromOnePersonToAnother(money, idCard);
    }
}
