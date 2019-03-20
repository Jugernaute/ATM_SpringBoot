package ua.com.codelions.service.creditCard;

import ua.com.codelions.entity.CreditCard;

import java.util.List;

public interface CreditCardService {
    void saveCreditCard(CreditCard creditCard);
    List<CreditCard> findAll();
}
