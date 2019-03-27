package ua.com.codelions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ua.com.codelions.entity.Bills;
import ua.com.codelions.entity.Person;
import ua.com.codelions.service.persone.PersonService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

    @Component
    public class Methods  {
        @Autowired
        PersonService personService;

        boolean multipleBills(BigDecimal num){
            Bills[] values = Bills.values();
            for (Bills b : values){
                int index = b.index();
                if (num.remainder(new BigDecimal(index)).equals(BigDecimal.ZERO)){
                    return true;
                }
            }
            return false;
        }

        public Map plusMoneyIfTrueAndMinusIfFalse (String sum, boolean plus){
            BigDecimal bigDecimalSum = new BigDecimal(sum);
            BigDecimal newSum = BigDecimal.ZERO;
            Map<String, String> map = new HashMap<>();
            if (!bigDecimalSum.equals(BigDecimal.ZERO) && multipleBills(bigDecimalSum)){
                String name = SecurityContextHolder.getContext().getAuthentication().getName();
                Person person = personService.findByUsername(name);
                BigDecimal money = person.getCreditCard().getMoney();
                if (plus){
                    newSum = money.add(bigDecimalSum);
                }else if (!plus){
                    newSum = money.subtract(bigDecimalSum);
                    if (newSum.compareTo(BigDecimal.ZERO)<0){
                        map.put("error", "take too much money");
                        return map;
                    }
                }
                person.getCreditCard().setMoney(newSum);
                personService.savePersone(person);
                map.put("result", newSum.toString());
                return map;
            }
            map.put("error", "wrong number");
            return map;
        }

        public Map moneyFromOnePersonToAnother (String sum, int idCard){
            BigDecimal bigDecimalSum = new BigDecimal(sum);
            Map<String, String> map = new HashMap<>();
            String personByIdCreditCard = personService.findPersonByIdCreditCard(idCard);
            if(personByIdCreditCard==null){
                map.put("error", "enter right idCreditCart");
                return map;
            }
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            Person currentPerson = personService.findByUsername(name);
            BigDecimal currentMoney = currentPerson.getCreditCard().getMoney();
            if (bigDecimalSum.compareTo(currentMoney)<=0){
                Person transferPerson = personService.findByUsername(personByIdCreditCard);
                if (transferPerson.equals(currentPerson)){
                    map.put("error", "you can not send money to yourself");
                    return map;
                }
                BigDecimal transferPersonMoney = transferPerson.getCreditCard().getMoney();
                BigDecimal newMoney = currentMoney.subtract(bigDecimalSum);
                currentPerson.getCreditCard().setMoney(newMoney);
                personService.savePersone(currentPerson);
                transferPerson.getCreditCard().setMoney(transferPersonMoney.add(bigDecimalSum));
                personService.savePersone(transferPerson);
                map.put("success", newMoney.toString());
                return map;
            }
            if (currentMoney.equals(BigDecimal.ZERO)){
                map.put("error", "on your account 0");
                return map;
            }
            map.put("error", "enter a smaller amount");
            return map;
        }
    }
