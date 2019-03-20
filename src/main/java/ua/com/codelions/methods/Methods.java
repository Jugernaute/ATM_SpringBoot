package ua.com.codelions.methods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ua.com.codelions.entity.Bills;
import ua.com.codelions.entity.Person;
import ua.com.codelions.service.persone.PersonService;

import java.util.HashMap;
import java.util.Map;

@Component
public class Methods extends MethodAbstract {
    @Autowired
    PersonService personService;

    @Override
     boolean multipleBills(int num){
        Bills[] values = Bills.values();
        for (Bills b : values){
            int index = b.index();
            if (num%index==0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Map plusMoneyIfTrueAndMinusIfFalse (int sum, boolean plus){
        int newSum = 0;
        Map<String, String> map = new HashMap<>();
        if (sum>0 && multipleBills(sum)){
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            Person person = personService.findByUsername(name);
            int money = person.getCreditCard().getMoney();
            if (plus){
                newSum = money+sum;
            }else if (!plus){
                newSum = money-sum;
                if (newSum<0){
                    map.put("error", "take too much money");
                    return map;
                }
            }
            person.getCreditCard().setMoney(newSum);
            personService.savePersone(person);
            map.put("result", Integer.toString(newSum));
            return map;
        }
        map.put("error", "wrong number");
        return map;
    }

    @Override
    public Map moneyFromOnePersonToAnother (int sum, int idCard){
        Map<String, String> map = new HashMap<>();
        String personByIdCreditCard = personService.findPersonByIdCreditCard(idCard);
        if(personByIdCreditCard==null){
            map.put("error", "enter right idCreditCart");
            return map;
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Person currentPerson = personService.findByUsername(name);
        int currentMoney = currentPerson.getCreditCard().getMoney();
        if (sum<=currentMoney){
            Person transferPerson = personService.findByUsername(personByIdCreditCard);
            if (transferPerson.equals(currentPerson)){
                map.put("error", "you can not send money to yourself");
                return map;
            }
            int transferPersonMoney = transferPerson.getCreditCard().getMoney();
            int newMoney = currentMoney - sum;
            currentPerson.getCreditCard().setMoney(newMoney);
            personService.savePersone(currentPerson);
            transferPerson.getCreditCard().setMoney(transferPersonMoney+sum);
            personService.savePersone(transferPerson);
            map.put("success", Integer.toString(newMoney));
            return map;
        }
        if (currentMoney==0){
           map.put("error", "on your account 0");
           return map;
        }
        map.put("error", "enter a smaller amount");
        return map;
    }
}
