package cv.user.api.repo;

import cv.user.api.entity.CompanyInfo;
import cv.user.api.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CurrencyRepo extends JpaRepository<Currency, String> {
    @Query("select o from Currency o where date(o.updatedDate) > :updatedDate")
    List<Currency> getCurrencyByDate(@Param("updatedDate") Date updatedDate);
}
