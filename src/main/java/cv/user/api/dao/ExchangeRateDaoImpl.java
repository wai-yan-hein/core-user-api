package cv.user.api.dao;

import cv.user.api.common.UserFilter;
import cv.user.api.entity.ExchangeKey;
import cv.user.api.entity.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Slf4j
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
        String sql = "select o from ExchangeRate o where date(o.exDate) between '" + fromDate + "' and '" + toDate + "' and deleted =false\n";
        String filter = "";
        if (!targetCur.equals("-")) {
            filter += "and o.targetCur = '" + targetCur + "'";
        }
        if (!compCode.equals("-")) {
            filter += "and o.key.compCode = '" + compCode + "'";
        }

        return findHSQL(sql + filter);
    }

    @Override
    public ExchangeRate getAvgRate(UserFilter filter) {
        String fromDate = filter.getFromDate();
        String toDate = filter.getToDate();
        String compCode = filter.getCompCode();
        String homeCur = filter.getHomeCur();
        String targetCur = filter.getTargetCur();
        String sql = """
                select home_cur,target_cur,round(avg(home_factor/target_factor),3) avg_rate
                from exchange_rate
                where date(ex_date) between ? and ?
                and comp_code=?
                and home_cur =?
                and target_cur=?
                and deleted =false
                group by home_cur;""";
        ResultSet rs = getResult(sql, fromDate, toDate, compCode, homeCur, targetCur);
        try {
            if (rs.next()) {
                ExchangeRate rate = new ExchangeRate();
                rate.setHomeCur(rs.getString("home_cur"));
                rate.setTargetCur(rs.getString("target_cur"));
                rate.setExRate(rs.getDouble("avg_rate"));
                return rate;
            }
        } catch (Exception e) {
            log.error("getAvgRate : " + e.getMessage());
        }
        return null;
    }

    @Override
    public ExchangeRate getRecentRate(UserFilter filter) {
        String fromDate = filter.getFromDate();
        String toDate = filter.getToDate();
        String compCode = filter.getCompCode();
        String homeCur = filter.getHomeCur();
        String targetCur = filter.getTargetCur();
        String sql = """
                select home_cur,target_cur,round((home_factor/target_factor),3) recent_rate
                from exchange_rate
                where date(ex_date) between ? and ?
                and comp_code=?
                and home_cur =?
                and target_cur=?
                and deleted =false
                order by ex_date desc
                limit 1;""";
        ResultSet rs = getResult(sql, fromDate, toDate, compCode, homeCur, targetCur);
        try {
            if (rs.next()) {
                ExchangeRate rate = new ExchangeRate();
                rate.setHomeCur(rs.getString("home_cur"));
                rate.setTargetCur(rs.getString("target_cur"));
                rate.setExRate(rs.getDouble("recent_rate"));
                return rate;
            }
        } catch (Exception e) {
            log.error("getRecentRate : " + e.getMessage());
        }
        return null;
    }
}
