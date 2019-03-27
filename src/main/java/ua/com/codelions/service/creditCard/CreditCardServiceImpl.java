package ua.com.codelions.service.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codelions.dao.CreditCardDao;
import ua.com.codelions.entity.CreditCard;

import java.util.List;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardDao creditCardDao;

    @Override
    public void saveCreditCard(CreditCard creditCard) {
        if (creditCard != null) {
            creditCardDao.save(creditCard);
        }
    }

    @Override
    public List<CreditCard> findAll() {
        return creditCardDao.findAll();
    }
}
