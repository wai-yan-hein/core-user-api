package cv.user.api.dao;

import cv.user.api.entity.ExchangeKey;
import cv.user.api.entity.ExchangeRate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExchangeRateDaoImpl extends AbstractDao<ExchangeKey, ExchangeRate> implements ExchangeRateDao {
    @Override
    public ExchangeRate save(ExchangeRate ex) {
        saveOrUpdate(ex, ex.getKey());
        return ex;
    }

    @Override
    public boolean delete(ExchangeKey key) {
        remove(key);
        return true;
    }

    @Override
    public List<ExchangeRate> search(String fromDate, String toDate, String targetCur, String compCode) {
        return null;
    }
}
