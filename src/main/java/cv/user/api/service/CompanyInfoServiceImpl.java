package cv.user.api.service;

import com.google.gson.reflect.TypeToken;
import cv.user.api.common.Util1;
import cv.user.api.common.YearEnd;
import cv.user.api.dao.NativeSqlDao;
import cv.user.api.entity.*;
import cv.user.api.model.AccSetting;
import cv.user.api.model.ChartOfAccount;
import cv.user.api.model.DepartmentA;
import cv.user.api.model.Location;
import cv.user.api.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

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
    private PrivilegeMenuRepo privilegeMenuRepo;
    @Autowired
    private NativeSqlDao nativeSqlDao;
    @Autowired
    private BusinessTypeService businessTypeService;
    @Autowired
    private DepartmentService departmentService;
    private WebRepo webRepo;

    @Override
    public CompanyInfo save(CompanyInfo info) {
        String compCode = info.getCompCode();
        if (Util1.isNullOrEmpty(compCode)) {
            info.setCompCode(getCompCode());
            info.setUserCode(Util1.isNull(info.getUserCode(), compCode));
            BusinessType type = businessTypeService.findById(info.getBusId());
            if (type != null) {
                webRepo = new WebRepo(info.getToken());
                processTemplate(info.getCompCode(), type.getBusName(),info.getCreatedBy());
            }
        }
        return infoRepo.save(info);
    }

    public List<File> getTemplateFiles(String busName) {
        String path = "template/" + busName.toLowerCase();
        File file = new File(path);
        return Arrays.asList(Objects.requireNonNull(file.listFiles()));
    }

    private void processTemplate(String compCode, String busName,String createdBy) {
        List<File> files = getTemplateFiles(busName);
        files.forEach((t) -> {
            String fileName = t.getName();
            String name = fileName.substring(0, fileName.lastIndexOf('.'));
            switch (name) {
                case "menu" -> saveMenu(t, compCode);
                case "department" -> saveDepartment(t, compCode);
                case "system_property" -> saveSysProperty(t, compCode);
                case "acc_setting" -> saveAccSetting(t, compCode);
                case "location" -> saveLocation(t, compCode);
                case "acc_department" -> saveDepartmentA(t, compCode);
                case "coa" -> saveCOA(t, compCode,createdBy);
            }
        });
    }

    private void saveMenu(File file, String compCode) {
        try {
            log.info("menu starting...");
            try (FileReader reader = new FileReader(file)) {
                Type type = new TypeToken<List<MenuTemplate>>() {
                }.getType();
                List<MenuTemplate> list = Util1.gson.fromJson(reader, type);
                list.forEach((m) -> {
                    Menu menu = new Menu();
                    MenuKey mKey = new MenuKey();
                    mKey.setMenuCode(m.getKey().getMenuId().toString());
                    mKey.setCompCode(compCode);
                    menu.setKey(mKey);
                    menu.setUserCode(null); //
                    menu.setMenuClass(m.getMenuClass());
                    menu.setMenuName(m.getMenuName());
                    menu.setMenuUrl(m.getMenuUrl());
                    menu.setParentMenuCode(m.getParentMenuId() == 0 ? "#" : m.getParentMenuId().toString());//
                    menu.setMenuType(m.getMenuType());
                    menu.setAccount(m.getAccount());
                    menu.setOrderBy(m.getOrderBy());
                    menu = menuService.save(menu);
                    savePrivileges(menu.getKey().getMenuCode(), compCode);
                    updateRole(compCode);
                });
                log.info("menu completed.");
            }
        } catch (IOException e) {
            log.error("saveMenu : " + e.getMessage());
        }
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

    private void saveDepartment(File file, String compCode) {
        try {
            log.info("department starting...");
            try (FileReader reader = new FileReader(file)) {
                Type type = new TypeToken<List<Department>>() {
                }.getType();
                List<Department> list = Util1.gson.fromJson(reader, type);
                list.forEach(d -> {
                    d.getKey().setCompCode(compCode);
                    departmentService.save(d);
                });
                log.info("department completed.");
            }

        } catch (Exception e) {
            log.error("saveDepartment : " + e.getMessage());
        }
    }

    private void saveSysProperty(File file, String compCode) {
        try {
            log.info("system property starting...");
            try (FileReader reader = new FileReader(file)) {
                Type type = new TypeToken<HashMap<String, String>>() {
                }.getType();
                HashMap<String, String> hm = Util1.gson.fromJson(reader, type);
                hm.forEach((key, value) -> {
                    SystemProperty p = new SystemProperty();
                    PropertyKey pk = new PropertyKey();
                    pk.setCompCode(compCode);
                    pk.setPropKey(key);
                    p.setPropValue(value);
                    p.setKey(pk);
                    systemPropertyRepo.save(p);
                });
                log.info("system property completed.");
            }

        } catch (Exception e) {
            log.error("saveSysProperty : " + e.getMessage());
        }
    }

    private void saveAccSetting(File file, String compCode) {
        try {
            log.info("account setting starting...");
            try (FileReader reader = new FileReader(file)) {
                Type type = new TypeToken<List<AccSetting>>() {
                }.getType();
                List<AccSetting> list = Util1.gson.fromJson(reader, type);
                list.forEach(d -> {
                    d.getKey().setCompCode(compCode);
                    webRepo.save(d).subscribe();
                });
                log.info("account setting completed.");
            }

        } catch (Exception e) {
            log.error("saveAccSetting : " + e.getMessage());
        }
    }

    private void saveLocation(File file, String compCode) {
        try {
            log.info("location starting...");
            try (FileReader reader = new FileReader(file)) {
                Type type = new TypeToken<List<Location>>() {
                }.getType();
                List<Location> list = Util1.gson.fromJson(reader, type);
                list.forEach(d -> {
                    d.getKey().setCompCode(compCode);
                    webRepo.save(d).subscribe();
                });
                log.info("location completed.");
            }
        } catch (Exception e) {
            log.error("saveLocation : " + e.getMessage());
        }
    }

    private void saveDepartmentA(File file, String compCode) {
        try {
            log.info("department account starting...");
            try (FileReader reader = new FileReader(file)) {
                Type type = new TypeToken<List<DepartmentA>>() {
                }.getType();
                List<DepartmentA> list = Util1.gson.fromJson(reader, type);
                list.forEach(d -> {
                    d.getKey().setCompCode(compCode);
                    webRepo.save(d).subscribe();
                });
                log.info("department account completed.");
            }
        } catch (Exception e) {
            log.error("saveDepartmentA : " + e.getMessage());
        }
    }

    private void saveCOA(File file, String compCode,String createdBy) {
        try {
            log.info("coa starting...");
            try (FileReader reader = new FileReader(file)) {
                Type type = new TypeToken<List<ChartOfAccount>>() {
                }.getType();
                List<ChartOfAccount> list = Util1.gson.fromJson(reader, type);
                list.forEach(d -> {
                    d.getKey().setCompCode(compCode);
                    d.setCreatedBy(createdBy);
                    d.setCreatedDate(LocalDateTime.now());
                    d.setActive(true);
                    d.setOption("USR");
                    webRepo.save(d).subscribe();
                });
                log.info("coa completed.");
            }
        } catch (Exception e) {
            log.error("saveCOA : " + e.getMessage());
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
