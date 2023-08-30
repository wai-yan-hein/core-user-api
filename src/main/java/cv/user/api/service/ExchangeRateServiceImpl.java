package cv.user.api.service;

import cv.user.api.common.UserFilter;
import cv.user.api.common.Util1;
import cv.user.api.dao.ExchangeRateDao;
import cv.user.api.entity.ExchangeKey;
import cv.user.api.entity.ExchangeRate;
import cv.user.api.entity.SeqKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Autowired
    private ExchangeRateDao dao;
    @Autowired
    private SeqService seqService;

    @Override
    public ExchangeRate save(ExchangeRate ex) {
        if (Util1.isNullOrEmpty(ex.getKey().getExCode())) {
            ex.getKey().setExCode(getExId());
        }
        return dao.save(ex);
    }

    @Override
    public boolean delete(ExchangeKey key) {
        return dao.delete(key);
    }

    @Override
    public List<ExchangeRate> search(String fromDate, String toDate, String targetCur, String compCode) {
        return dao.search(fromDate, toDate, targetCur, compCode);
    }

    @Override
    public ExchangeRate getAvgRate(UserFilter filter) {
        return dao.getAvgRate(filter);
    }

    @Override
    public ExchangeRate getRecentRate(UserFilter filter) {
        return dao.getRecentRate(filter);
    }

    @Override
    public List<ExchangeRate> getExchangeRate(String compCode) {
        return dao.getExchangeRate(compCode);
    }

    private String getExId() {
        String option = "Exchange";
        String period = Util1.toDateStr(Util1.getTodayDate(), "MMyy");
        int seqNo = seqService.getSeqNo(new SeqKey(option, period));
        return period + "-" + String.format("%0" + 5 + "d", seqNo);
    }
}
