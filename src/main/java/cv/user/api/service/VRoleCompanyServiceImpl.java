package cv.user.api.service;

import cv.user.api.dao.VRoleCompanyDao;
import cv.user.api.entity.VRoleCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VRoleCompanyServiceImpl implements VRoleCompanyService{
    @Autowired
    private VRoleCompanyDao dao;
    @Override
    public List<VRoleCompany> getPrivilegeCompany(String roleCode) {
        return dao.getPrivilegeCompany(roleCode);
    }
}
