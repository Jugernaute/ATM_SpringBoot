package ua.com.codelions.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.codelions.entity.CreditCard;

public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {
}
