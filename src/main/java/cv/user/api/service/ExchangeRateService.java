package cv.user.api.service;

import cv.user.api.entity.ExchangeKey;
import cv.user.api.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {
    ExchangeRate save(ExchangeRate ex);
    boolean delete(ExchangeKey key);

    List<ExchangeRate> search(String fromDate, String toDate, String targetCur, String compCode);
}
