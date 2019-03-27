package ua.com.codelions.service.persone;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.com.codelions.entity.Person;


public interface PersonService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    void savePersone(Person person);
    Person findByUsername(String username);
    String findPersonByIdCreditCard(int creditCard);
//    Person getAuthoritiesPerson (SecurityContextHolder contextHolder);
}
