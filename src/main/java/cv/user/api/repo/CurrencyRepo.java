package cv.user.api.repo;

import cv.user.api.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepo extends JpaRepository<Currency, String> {
}
