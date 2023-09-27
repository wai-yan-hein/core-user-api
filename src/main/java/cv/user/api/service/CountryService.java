package cv.user.api.service;

import cv.user.api.entity.Country;

import java.util.List;

public interface CountryService {
    Country save(Country country);

    List<Country> getCountry();

    Country findCountry(String id);
    void delete(String id);
}
