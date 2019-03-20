package ua.com.codelions.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.codelions.entity.Person;

public interface PersonDao extends JpaRepository<Person, Integer> {
    Person findByUsername(String username);
    @Query(value = "select username FROM person LEFT JOIN credit_card c on person.id_person = c.person_id_person where id_credit_card=:idCard", nativeQuery = true)
    String findPersonByIdCreditCard(@Param("idCard") int idCard);
}
