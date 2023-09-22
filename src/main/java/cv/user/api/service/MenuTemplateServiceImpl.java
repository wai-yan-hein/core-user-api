package cv.user.api.service;

import cv.user.api.entity.MenuTemplate;
import cv.user.api.entity.MenuTemplateKey;
import cv.user.api.repo.MenuTemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuTemplateServiceImpl implements MenuTemplateService {
    @Autowired
    private MenuTemplateRepo repo;

    @Override
    public MenuTemplate save(MenuTemplate menu) {
        MenuTemplateKey key = menu.getKey();
        if (key != null) {
            if (key.getMenuId() == null) {
                key.setMenuId(repo.findMaxId());
            }
        }
        return repo.save(menu);
    }

    @Override
    public boolean delete(MenuTemplateKey key) {
        repo.deleteById(key);
        return true;
    }

    @Override
    public List<MenuTemplate> getMenuTree(Integer busId) {
        return getMenuList(busId);
    }

    @Override
    public List<MenuTemplate> getMenu(Integer busId) {
        return repo.findAll(busId);
    }


    private List<MenuTemplate> getMenuList(Integer busId) {
        List<MenuTemplate> menus = repo.getMenuChild(0, busId);
        if (!menus.isEmpty()) {
            for (MenuTemplate m : menus) {
                getMenuChild(m);
            }
        }
        return menus;
    }

    private void getMenuChild(MenuTemplate parent) {
        List<MenuTemplate> menus = repo.getMenuChild(parent.getKey().getMenuId(), parent.getKey().getBusId());
        parent.setChild(menus);
        if (!menus.isEmpty()) {
            for (MenuTemplate m : menus) {
                getMenuChild(m);
            }
        }
    }
}
