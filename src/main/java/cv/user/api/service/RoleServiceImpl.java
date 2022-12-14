package cv.user.api.service;

import cv.user.api.common.Util1;
import cv.user.api.entity.*;
import cv.user.api.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
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
            String exampleRole = role.getExampleRole();
            if (!Util1.isNullOrEmpty(exampleRole)) {
                createMenu(roleCode, exampleRole);
                createCompany(roleCode, exampleRole);
                createRoleProperty(roleCode, exampleRole);
            }
        }
        return repo.save(role);
    }

    private String getRoleCode() {
        String option = "Role";
        int seqNo = seqService.getSeqNo(new SeqKey(option, "-"));
        return "R-" + String.format("%0" + 3 + "d", seqNo);
    }

    private void createMenu(String roleCode, String exampleRole) {
        List<PrivilegeMenu> list = privilegeMenuRepo.getPrivilegeRole(exampleRole);
        for (PrivilegeMenu p : list) {
            PrivilegeMenu menu = new PrivilegeMenu();
            PMKey key = new PMKey();
            key.setRoleCode(roleCode);
<<<<<<< HEAD
            PrivilegeMenu menu = new PrivilegeMenu();
            menu.setKey(key);
            menu.setAllow(false);
=======
            key.setMenuCode(p.getKey().getMenuCode());
            menu.setKey(key);
            menu.setAllow(p.isAllow());
>>>>>>> 3175856dfb85e25f11512fe5523db80562388fdc
            privilegeMenuRepo.save(menu);
        }
        log.info("created menu.");

    }

    private void createCompany(String roleCode, String exampleRole) {
        List<PrivilegeCompany> list = privilegeCompanyRepo.getRoleCompany(exampleRole);
        for (PrivilegeCompany p : list) {
            PrivilegeCompany company = new PrivilegeCompany();
            PCKey key = new PCKey();
            key.setRoleCode(roleCode);
            key.setCompanyInfo(p.getKey().getCompanyInfo());
            company.setKey(key);
            company.setAllow(p.isAllow());
            privilegeCompanyRepo.save(company);
        }
    }

    private void createRoleProperty(String roleCode, String exampleRole) {
        List<RoleProperty> property = rolePropertyRepo.getRoleProperty(exampleRole);
        for (RoleProperty p : property) {
            RoleProperty role = new RoleProperty();
            RolePropertyKey key = new RolePropertyKey();
            key.setRoleCode(roleCode);
            key.setPropKey(p.getKey().getPropKey());
            role.setKey(key);
            role.setPropValue(p.getPropValue());
            rolePropertyRepo.save(role);
        }
    }

}
