package cv.user.api.service;

import cv.user.api.dao.CountryDao;
import cv.user.api.entity.Country;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao dao;

    @Override
    public Country save(Country country) {
        return dao.save(country);
    }

    @Override
    public List<Country> getCountry() {
        return dao.getCountry();
    }

    @Override
    public Country findCountry(String id) {
        return dao.findCountry(id);
    }

    @Override
    public void delete(String id) {

    }
}
