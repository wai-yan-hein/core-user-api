package cv.user.api.service;

import cv.user.api.common.Util1;
import cv.user.api.common.YearEnd;
import cv.user.api.entity.*;
import cv.user.api.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CompanyInfoServiceImpl implements CompanyInfoService {
    @Autowired
    private CompanyInfoRepo infoRepo;
    @Autowired
    private PrivilegeCompanyRepo companyRepo;
    @Autowired
    private AppRoleRepo roleRepo;
    @Autowired
    private SeqService seqService;
    @Autowired
    private SystemPropertyRepo systemPropertyRepo;
    @Autowired
    private RolePropertyRepo rolePropertyRepo;
    @Autowired
    private MenuService menuService;
    @Autowired
    MenuTemplateService menuTemplateService;
    @Autowired
    private PrivilegeMenuRepo privilegeMenuRepo;

    @Override
    public CompanyInfo save(CompanyInfo info) {
        if (Util1.isNullOrEmpty(info.getCompCode())) {
            String compCode = getCompCode();
            info.setCompCode(compCode);
            info.setUserCode(Util1.isNull(info.getUserCode(), compCode));
            updateRole(compCode);
            saveMenu(compCode, info.getBusId());
        }
        return infoRepo.save(info);
    }

    private void saveMenu(String compCode, Integer busId) {
        if (busId != null) {
            //get menu template with bus id
            List<MenuTemplate> mTemList = menuTemplateService.findAll(busId);
            mTemList.forEach(m -> {
                saveMenuAndPrivilege(m, compCode);
            });
        }
    }


    private void saveMenuAndPrivilege(MenuTemplate m, String compCode) {
        Menu menu = new Menu();
        MenuKey mKey = new MenuKey();
        mKey.setMenuCode(m.getKey().getMenuId().toString());
        mKey.setCompCode(compCode);
        menu.setKey(mKey);
        menu.setUserCode(null); //
        menu.setMenuClass(m.getMenuClass());
        menu.setMenuName(m.getMenuName());
        menu.setMenuUrl(m.getMenuUrl());
        menu.setParentMenuCode(m.getParentMenuId() == "0" ? "#" : m.getParentMenuId().toString());//
        menu.setMenuType(m.getMenuType());
        menu.setAccount(m.getAccount());
        menu.setOrderBy(m.getOrderBy());
        menu = menuService.save(menu);
        savePrivileges(menu.getKey().getMenuCode(), compCode);
    }

    private void savePrivileges(String menuCode, String compCode) {
        List<AppRole> roles = roleRepo.findAll();
        if (!roles.isEmpty()) {
            roles.forEach(r -> {
                PrivilegeMenu p = new PrivilegeMenu();
                PMKey key = new PMKey();
                key.setCompCode(compCode);
                key.setRoleCode(r.getRoleCode());
                key.setMenuCode(menuCode);
                p.setKey(key);
                p.setAllow(true);
                privilegeMenuRepo.save(p);
            });
        }
    }

    @Override
    public YearEnd yearEnd(YearEnd end) {
        String yeCompCode = end.getYeCompCode();
        Optional<CompanyInfo> option = infoRepo.findById(yeCompCode);
        if (option.isPresent()) {
            //year-end company
            CompanyInfo ye = option.get();
            ye.setBatchLock(end.isBatchLock());
            ye.setYearEndDate(end.getYearEndDate());
            //new company
            CompanyInfo info = new CompanyInfo();
            info.setCompName(ye.getCompName() + "-" + Util1.toDateStr(end.getStartDate(), "yyyy"));
            info.setActive(true);
            info.setBusId(ye.getBusId());
            info.setCompAddress(ye.getCompAddress());
            info.setCompEmail(ye.getCompEmail());
            info.setCompPhone(ye.getCompPhone());
            info.setCurCode(ye.getCurCode());
            info.setCreatedBy(end.getCreateBy());
            info.setCreatedDate(end.getCreatedDate());
            info.setStartDate(end.getStartDate());
            info.setEndDate(end.getEndDate());
            info = save(info);
            ye.setBatchLock(end.isBatchLock());
            ye.setEndDate(end.getYearEndDate());
            ye.setCompName(ye.getCompName() + "-" + Util1.toDateStr(end.getStartDate(), "yyyy"));
            log.info("copied company.");
            String compCode = info.getCompCode();
            end.setCompCode(compCode);
            if (compCode != null) {
                updateYECompany(ye);
                copySysProp(compCode, yeCompCode);
                copyRoleProp(compCode, yeCompCode);
                copyMenu(compCode, yeCompCode);
                copyPrivilegeMenu(compCode, yeCompCode);
                end.setMessage("year end in user.");
                return end;
            }
        }
        end.setMessage("something went wrong.");
        return end;
    }

    private void updateYECompany(CompanyInfo info) {
        infoRepo.save(info);
    }

    private String getCompCode() {
        String option = "Company";
        int seqNo = seqService.getSeqNo(new SeqKey(option, "-"));
        return String.format("%0" + 2 + "d", seqNo + 1);
    }

    private void updateRole(String compCode) {
        List<AppRole> roles = roleRepo.findAll();
        for (AppRole r : roles) {
            PCKey key = new PCKey();
            key.setRoleCode(r.getRoleCode());
            key.setCompCode(compCode);
            PrivilegeCompany pc = new PrivilegeCompany();
            pc.setKey(key);
            pc.setAllow(true);
            companyRepo.save(pc);
        }
    }

    private void copySysProp(String compCode, String exampleCompCode) {
        systemPropertyRepo.getSystemProperty(exampleCompCode).forEach(property -> {
            SystemProperty p = new SystemProperty();
            PropertyKey key = new PropertyKey();
            key.setCompCode(compCode);
            key.setPropKey(property.getKey().getPropKey());
            p.setKey(key);
            p.setPropValue(property.getPropValue());
            p.setRemark(property.getRemark());
            systemPropertyRepo.save(p);
        });
        log.info("copied system property.");
    }

    private void copyRoleProp(String compCode, String exampleCompCode) {
        rolePropertyRepo.getRole(exampleCompCode).forEach(property -> {
            RoleProperty p = new RoleProperty();
            RolePropertyKey key = new RolePropertyKey();
            key.setCompCode(compCode);
            key.setPropKey(property.getKey().getPropKey());
            key.setRoleCode(property.getKey().getRoleCode());
            p.setKey(key);
            p.setPropValue(property.getPropValue());
            p.setRemark(property.getRemark());
            rolePropertyRepo.save(p);
        });
        log.info("copied role property.");
    }

    private void copyMenu(String compCode, String exampleCompCode) {
        menuService.getMenu(exampleCompCode).forEach(menu -> {
            Menu m = new Menu();
            MenuKey key = new MenuKey();
            key.setCompCode(compCode);
            key.setMenuCode(menu.getKey().getMenuCode());
            m.setKey(key);
            m.setMenuClass(menu.getMenuClass());
            m.setMenuName(menu.getMenuName());
            m.setMenuType(menu.getMenuType());
            m.setAccount(menu.getAccount());
            m.setMenuUrl(menu.getMenuUrl());
            m.setOrderBy(menu.getOrderBy());
            m.setParentMenuCode(menu.getParentMenuCode());
            m.setUserCode(menu.getUserCode());
            menuService.save(m);
        });
        log.info("copied role property.");
    }

    private void copyPrivilegeMenu(String compCode, String exampleCompCode) {
        privilegeMenuRepo.getPrivilegeCompany(exampleCompCode).forEach(menu -> {
            PMKey key = new PMKey();
            key.setCompCode(compCode);
            key.setMenuCode(menu.getKey().getMenuCode());
            key.setRoleCode(menu.getKey().getRoleCode());
            PrivilegeMenu m = new PrivilegeMenu();
            m.setKey(key);
            m.setAllow(menu.isAllow());
            privilegeMenuRepo.save(m);
        });
        log.info("copied privilege menu.");
    }
}
