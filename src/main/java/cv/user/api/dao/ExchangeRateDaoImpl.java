package cv.user.api.dao;

import cv.user.api.common.UserFilter;
import cv.user.api.entity.ExchangeKey;
import cv.user.api.entity.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
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
    public ExchangeRate findById(ExchangeKey key) {
        return getByKey(key);
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

    @Override
    public List<ExchangeRate> getExchangeRate(String compCode) {
        List<ExchangeRate> list = new ArrayList<>();
        String sql = """
                select ex_code,comp_code,ex_date,home_cur,target_cur,home_factor,target_factor\s
                from exchange_rate
                where comp_code=?
                and deleted =false
                order by ex_date desc
                limit 20;""";
        try {
            ResultSet rs = getResult(sql, compCode);
            while (rs.next()) {
                //ex_code,comp_code,ex_date,home_cur,target_cur,home_factor,target_factor
                ExchangeRate r = new ExchangeRate();
                ExchangeKey key = new ExchangeKey();
                key.setCompCode(rs.getString("comp_code"));
                key.setExCode(rs.getString("ex_code"));
                r.setKey(key);
                r.setExDate(rs.getTimestamp("ex_date").toLocalDateTime());
                r.setHomeCur(rs.getString("home_cur"));
                r.setTargetCur(rs.getString("target_cur"));
                r.setHomeFactor(rs.getDouble("home_factor"));
                r.setTargetFactor(rs.getDouble("target_factor"));
                list.add(r);
            }
        } catch (Exception e) {
            log.error("getExchangeRate : " + e.getMessage());
        }
        return list;
    }
}
