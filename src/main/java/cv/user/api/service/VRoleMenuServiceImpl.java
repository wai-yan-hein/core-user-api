package cv.user.api.service;

import cv.user.api.dao.VRoleMenuDao;
import cv.user.api.entity.VRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VRoleMenuServiceImpl implements VRoleMenuService{
    @Autowired
    private VRoleMenuDao dao;
    @Override
    public List<VRoleMenu> getMenu(String roleCode, String parentCode, String compCode,boolean privilege) {
        return dao.getMenu(roleCode, parentCode, compCode,privilege);
    }

    @Override
    public List<VRoleMenu> getReport(String roleCode, String menuClass, String compCode) {
        return dao.getReport(roleCode, menuClass, compCode);
    }
}
