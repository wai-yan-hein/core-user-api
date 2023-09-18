package cv.user.api.dao;

import cv.user.api.entity.VRoleMenu;

import java.util.List;

public interface VRoleMenuDao {
    List<VRoleMenu> getMenu(String roleCode, String parentCode, String compCode,boolean privilege);
    List<VRoleMenu> getReport(String roleCode, String menuClass, String compCode);
}
