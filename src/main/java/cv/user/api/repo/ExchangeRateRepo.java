package cv.user.api.repo;

import cv.user.api.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface ExchangeRateRepo extends JpaRepository<ExchangeRate, String> {
    @Query("select o from ExchangeRate o where o.updatedDate > :updatedDate")
    List<ExchangeRate> getExchangeRateByDate(@Param("updatedDate") LocalDateTime updatedDate);
}
