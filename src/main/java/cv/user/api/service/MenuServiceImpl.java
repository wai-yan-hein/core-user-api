package cv.user.api.service;

import cv.user.api.common.Util1;
import cv.user.api.entity.*;
import cv.user.api.repo.AppRoleRepo;
import cv.user.api.repo.MenuRepo;
import cv.user.api.repo.PrivilegeMenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private AppRoleRepo roleRepo;
    @Autowired
    private SeqService seqService;
    @Autowired
    private PrivilegeMenuRepo privilegeMenuRepo;

    @Override
    public Menu save(Menu menu) {
        MenuKey key = menu.getKey();
        if (Util1.isNullOrEmpty(key.getMenuCode())) {
            key.setMenuCode(getMenuCode());
            updatePrivileges(key.getMenuCode(), key.getCompCode());
        }
        return menuRepo.save(menu);
    }

    @Override
    public List<Menu> getMenu(String compCode) {
        return menuRepo.getMenu(compCode);
    }


    private String getMenuCode() {
        String option = "Menu";
        String period = Util1.toDateStr(Util1.getTodayDate(), "MMyy");
        int seqNo = seqService.getSeqNo(new SeqKey(option, period));
        return period + "-" + String.format("%0" + 3 + "d", seqNo);
    }

    private void updatePrivileges(String menuCode, String compCode) {
        List<AppRole> roles = roleRepo.findAll();
        if (!roles.isEmpty()) {
            for (AppRole role : roles) {
                PrivilegeMenu p = new PrivilegeMenu();
                PMKey key = new PMKey();
                key.setCompCode(compCode);
                key.setRoleCode(role.getRoleCode());
                key.setMenuCode(menuCode);
                p.setKey(key);
                p.setAllow(true);
                privilegeMenuRepo.save(p);
            }
        }
    }
}
