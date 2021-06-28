package utn.ParcialesLabV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.ParcialesLabV.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
