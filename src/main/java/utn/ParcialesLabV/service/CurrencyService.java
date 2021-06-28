package utn.ParcialesLabV.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import utn.ParcialesLabV.model.Currency;
import utn.ParcialesLabV.repository.CurrencyRepository;

import java.util.List;
@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Currency getByID(Integer id) {
        return  currencyRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    public void add(Currency currency) {
        currencyRepository.save(currency);

    }

    public void deleteById(Integer id) {
        currencyRepository.deleteById(id);

    }
}
