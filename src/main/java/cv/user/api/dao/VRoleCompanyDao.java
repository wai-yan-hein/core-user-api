package cv.user.api.dao;

import cv.user.api.entity.VRoleCompany;

import java.util.List;

public interface VRoleCompanyDao {
    List<VRoleCompany> getPrivilegeCompany(String roleCode);
}
