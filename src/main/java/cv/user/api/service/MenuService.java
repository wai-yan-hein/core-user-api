package cv.user.api.service;

import cv.user.api.entity.Menu;
import cv.user.api.entity.VRoleMenu;

import java.util.List;

public interface MenuService {
    Menu save(Menu menu);

    List<VRoleMenu> getMenuChile(String roleCode, String compCode, boolean active);
}
