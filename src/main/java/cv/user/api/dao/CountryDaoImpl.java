package cv.user.api.dao;

import cv.user.api.entity.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class CountryDaoImpl extends AbstractDao<String, Country> implements CountryDao {
    @Override
    public Country save(Country country) {
        saveOrUpdate(country, country.getCode());
        return country;
    }

    @Override
    public List<Country> getCountry() {
        String sql = "select o from Country o";
        return findHSQL(sql);
    }

    @Override
    public Country findCountry(String id) {
        return getByKey(id);
    }

    @Override
    public void delete(String id) {

    }
}
