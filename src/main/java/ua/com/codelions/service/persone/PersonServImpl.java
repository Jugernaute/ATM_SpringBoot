package ua.com.codelions.service.persone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codelions.dao.PersonDao;
import ua.com.codelions.entity.Person;

@Transactional
@Service
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return personDao.findByUsername(s);
    }
}
