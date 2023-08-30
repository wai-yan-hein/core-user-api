package cv.user.api.dao;

import cv.user.api.common.UserFilter;
import cv.user.api.entity.ExchangeKey;
import cv.user.api.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateDao {
    ExchangeRate save(ExchangeRate ex);
    boolean delete(ExchangeKey key);

    List<ExchangeRate> search(String fromDate, String toDate, String targetCur, String compCode);
    ExchangeRate getAvgRate(UserFilter filter);
    ExchangeRate getRecentRate(UserFilter filter);
    List<ExchangeRate> getExchangeRate(String compCode);

}
