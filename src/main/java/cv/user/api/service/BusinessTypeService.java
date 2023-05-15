package cv.user.api.service;

import cv.user.api.entity.BusinessType;

import java.util.List;

public interface BusinessTypeService {
    BusinessType save(BusinessType obj);

    BusinessType findById(int id);

    int getMaxId();

    List<BusinessType> findAll();
}
