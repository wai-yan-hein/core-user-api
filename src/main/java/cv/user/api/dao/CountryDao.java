package cv.user.api.dao;

import cv.user.api.entity.Country;

import java.util.List;

public interface CountryDao {
    Country save(Country country);

    List<Country> getCountry();

    Country findCountry(String id);

    void delete(String id);
}
