package cv.user.api.service;

import cv.user.api.entity.BusinessType;
import cv.user.api.repo.BusinessTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusinessTypeServiceImpl implements BusinessTypeService {
    @Autowired
    private BusinessTypeRepo repo;

    @Override
    public BusinessType save(BusinessType obj) {
        if (obj.getBusId() == null) {
            int id = getMaxId();
            obj.setBusId(id);
        }
        return repo.save(obj);
    }

    @Override
    public BusinessType findById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public int getMaxId() {
        return repo.findMaxId();
    }

    @Override
    public List<BusinessType> findAll() {
        return repo.findAll();
    }
}
