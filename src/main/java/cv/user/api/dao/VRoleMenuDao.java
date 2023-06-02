package cv.user.api.dao;

import cv.user.api.entity.VRoleMenu;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VRoleMenuDao {
    List<VRoleMenu> getMenu(String roleCode, String parentCode, String compCode);
    List<VRoleMenu> getMenuChild(String roleCode, String parentCode, String compCode);
    List<VRoleMenu> getReport(String roleCode, String menuClass, String compCode);
}
