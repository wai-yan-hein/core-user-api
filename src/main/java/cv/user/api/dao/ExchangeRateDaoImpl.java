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
        String sql = "select o from ExchangeRate o where date(o.exDate) between '" + fromDate + "' and '" + toDate + "'\n";
        String filter = "";
        if (!targetCur.equals("-")) {
            filter += "and o.targetCur = '" + targetCur + "'";
        }
        if (!compCode.equals("-")) {
            filter += "and o.key.compCode = '" + compCode + "'";
        }

        return findHSQL(sql + filter);
    }
}
