package cv.user.api.service;

import cv.user.api.common.Util1;
import cv.user.api.entity.*;
import cv.user.api.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private AppRoleRepo repo;
    @Autowired
    private SeqService seqService;
    @Autowired
    private PrivilegeMenuRepo privilegeMenuRepo;
    @Autowired
    private PrivilegeCompanyRepo privilegeCompanyRepo;
    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private CompanyInfoRepo companyInfoRepo;
    @Autowired
    private RolePropertyRepo rolePropertyRepo;

    @Override
    public AppRole save(AppRole role) {
        if (Util1.isNullOrEmpty(role.getRoleCode())) {
            String roleCode = getRoleCode();
            role.setRoleCode(roleCode);
            updateMenu(roleCode);
            updateCompany(roleCode);
            updateRoleProperty(roleCode);
        }
        return repo.save(role);
    }

    private String getRoleCode() {
        String option = "Role";
        String period = Util1.toDateStr(Util1.getTodayDate(), "MMyy");
        int seqNo = seqService.getSeqNo(new SeqKey(option, period));
        return String.format("%0" + 3 + "d", seqNo);
    }

    private void updateMenu(String roleCode) {
        List<Menu> menus = menuRepo.findAll();
        for (Menu m : menus) {
            PMKey key = new PMKey();
            key.setMenuCode(m.getMenuCode());
            key.setRoleCode(roleCode);
            PrivilegeMenu menu = new PrivilegeMenu();
            menu.setPmKey(key);
            menu.setAllow(false);
            privilegeMenuRepo.save(menu);
        }
    }

    private void updateCompany(String roleCode) {
        List<CompanyInfo> companies = companyInfoRepo.findAll();
        for (CompanyInfo com : companies) {
            PCKey key = new PCKey();
            key.setCompanyInfo(com);
            key.setRoleCode(roleCode);
            PrivilegeCompany company = new PrivilegeCompany();
            company.setPcKey(key);
            company.setAllow(false);
            privilegeCompanyRepo.save(company);
        }
    }

    private void updateRoleProperty(String roleCode) {
        HashMap<String, String> hm = new HashMap<>();
        List<RoleProperty> property = rolePropertyRepo.findAll();
        for (RoleProperty p : property) {
            hm.put(p.getKey().getPropKey(), p.getPropValue());
        }
        if (!hm.isEmpty()) {
            hm.forEach((s, s2) -> {
                RolePropertyKey key = new RolePropertyKey();
                key.setRoleCode(roleCode);
                key.setPropKey(s);
                RoleProperty p = new RoleProperty();
                p.setKey(key);
                p.setPropValue(s2);
                rolePropertyRepo.save(p);
            });
        }

    }

}
