package cv.user.api.service;

import cv.user.api.entity.VRoleCompany;

import java.util.List;

public interface VRoleCompanyService {
    List<VRoleCompany> getPrivilegeCompany(String roleCode);
}
