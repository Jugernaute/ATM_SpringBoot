package ua.com.codelions.service.persone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codelions.dao.PersonDao;
import ua.com.codelions.entity.Person;


@Service
@Component
@Transactional
public class PersonServImpl implements PersonService {
    @Autowired
    PersonDao personDao;


    @Override
    public void savePersone(Person person) {
        if (person!=null){
            personDao.save(person);
        }
    }

    @Override
    public Person findByUsername(String username) {
        return personDao.findByUsername(username);
    }

    @Override
    public String findPersonByIdCreditCard(int creditCard) {
        return personDao.findPersonByIdCreditCard(creditCard);
    }

//    @Override
//    public Person getAuthoritiesPerson(SecurityContextHolder holder) {
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        return personDao.findByUsername(name);
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return personDao.findByUsername(s);
    }
}

