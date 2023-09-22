package cv.user.api.service;

import cv.user.api.entity.MenuTemplate;
import cv.user.api.entity.MenuTemplateKey;

import java.util.List;

public interface MenuTemplateService {
    MenuTemplate save(MenuTemplate menu);
    boolean delete(MenuTemplateKey key);

    List<MenuTemplate> getMenuTree(Integer busId);
    List<MenuTemplate> getMenu(Integer busId);

}
