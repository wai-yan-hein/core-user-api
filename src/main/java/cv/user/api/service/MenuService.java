package cv.user.api.service;

import cv.user.api.entity.Menu;

import java.util.List;

public interface MenuService {
    Menu save(Menu menu);

    List<Menu> getMenu(String compCode);
}
