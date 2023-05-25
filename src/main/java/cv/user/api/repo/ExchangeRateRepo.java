package cv.user.api.repo;

import cv.user.api.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ExchangeRateRepo extends JpaRepository<ExchangeRate, String> {
    @Query("select o from ExchangeRate o where date(o.updatedDate) > :updatedDate")
    List<ExchangeRate> getExchangeRateByDate(@Param("updatedDate") Date updatedDate);
}
