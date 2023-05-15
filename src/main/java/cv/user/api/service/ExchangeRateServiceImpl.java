package cv.user.api.service;

import cv.user.api.dao.ExchangeRateDao;
import cv.user.api.entity.ExchangeKey;
import cv.user.api.entity.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ExchangeRateServiceImpl implements ExchangeRateService{
    @Autowired
    private ExchangeRateDao dao;
    @Override
    public ExchangeRate save(ExchangeRate ex) {
        return dao.save(ex);
    }

    @Override
    public boolean delete(ExchangeKey key) {
        return dao.delete(key);
    }

    @Override
    public List<ExchangeRate> search(String fromDate, String toDate, String targetCur, String compCode) {
        return dao.search(fromDate,toDate,targetCur,compCode);
    }
}
