package cv.user.api.service;

import cv.user.api.entity.AppRole;

public interface RoleService {
    AppRole save(AppRole role);
    AppRole findById(String roleCode);
}
