package cv.user.api.service;

import cv.user.api.entity.MenuTemplate;

import java.util.List;

public interface MenuTemplateService {
    MenuTemplate save(MenuTemplate menu);

    List<MenuTemplate> getMenu(Integer busId);
    List<MenuTemplate> findAll(Integer busId);
}
