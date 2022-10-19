package cv.user.api.controller;

import cv.user.api.common.ReturnObject;
import cv.user.api.entity.*;
import cv.user.api.repo.*;
import cv.user.api.service.AppUserService;
import cv.user.api.service.CompanyInfoService;
import cv.user.api.service.MenuService;
import cv.user.api.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AppUserRepo userRepo;
    @Autowired
    private VRoleMenuRepo vRoleMenuRepo;
    @Autowired
    private VRoleCompanyRepo vRoleCompanyRepo;
    @Autowired
    private PrivilegeMenuRepo privilegeMenuRepo;
    @Autowired
    private RolePropertyRepo rolePropertyRepo;
    @Autowired
    private AppRoleRepo appRoleRepo;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MachineInfoRepo machineInfoRepo;
    @Autowired
    private PrivilegeCompanyRepo privilegeCompanyRepo;
    @Autowired
    private CompanyInfoRepo companyInfoRepo;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CurrencyRepo currencyRepo;
    @Autowired
    private CompanyInfoService companyInfoService;
    @Autowired
    private SystemPropertyRepo systemPropertyRepo;
    @Autowired
    private MacPropertyRepo macPropertyRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    private final ReturnObject ro = new ReturnObject();

    @GetMapping("/hello")
    public String hello() {
        return "Hello Back.";
    }

    @GetMapping("/login")
    public ResponseEntity<AppUser> login(@RequestParam String userName, @RequestParam String password) {
        AppUser user = userRepo.login(userName, password);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-mac-info")
    public ResponseEntity<MachineInfo> getMacInfo(@RequestParam String macName) {
        MachineInfo mac = new MachineInfo();
        mac.setMacId(0);
        List<MachineInfo> byName = machineInfoRepo.findByName(macName);
        if (!byName.isEmpty()) {
            mac = byName.get(0);

        }
        return ResponseEntity.ok(mac);
    }

    @GetMapping("/get-mac-list")
    public ResponseEntity<?> getMacList() {
        return ResponseEntity.ok(machineInfoRepo.findAll());
    }

    @GetMapping("/update-program")
    public String updateProgram(@RequestParam Integer macId) {
        List<MachineInfo> macs = machineInfoRepo.findAll();
        for (MachineInfo mac : macs) {
            if (!Objects.equals(mac.getMacId(), macId)) {
                mac.setProUpdate(false);

            }
            machineInfoRepo.save(mac);
        }
        return "Success Release Program Update.";
    }

    @PostMapping("/save-mac")
    public ResponseEntity<MachineInfo> saveMacInfo(@RequestBody MachineInfo machineInfo) {
        machineInfo = machineInfoRepo.save(machineInfo);
        return ResponseEntity.ok(machineInfo);
    }

    @PostMapping("/save-user")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
        user = appUserService.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/save-privilege-company")
    public ResponseEntity<PrivilegeCompany> savePC(@RequestBody PrivilegeCompany c) {
        c = privilegeCompanyRepo.save(c);
        return ResponseEntity.ok(c);
    }

    @GetMapping("/get-privilege-company")
    public ResponseEntity<List<PrivilegeCompany>> getPC(@RequestParam String roleCode) {
        return ResponseEntity.ok(privilegeCompanyRepo.getRoleCompany(roleCode));
    }

    @GetMapping("/get-appuser")
    public ResponseEntity<List<AppUser>> getAppUser() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    @GetMapping("/find-appuser")
    public ResponseEntity<AppUser> findAppUser(@RequestParam String userCode) {
        Optional<AppUser> byId = userRepo.findById(userCode);
        return ResponseEntity.ok(byId.orElse(null));
    }

    @GetMapping("/get-role-menu-tree")
    public ResponseEntity<List<VRoleMenu>> getRoleMenu(@RequestParam String roleCode) {
        return ResponseEntity.ok(getMenu(roleCode));
    }

    @GetMapping("/get-privilege-role-menu-tree")
    public ResponseEntity<List<VRoleMenu>> getPRoleMenu(@RequestParam String roleCode) {
        List<VRoleMenu> menus = getRoleMenuTree(roleCode);
        menus.removeIf(m -> !m.isAllow());
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/get-menu-tree")
    public ResponseEntity<List<Menu>> getMenu() {
        return ResponseEntity.ok(getMenuTree());
    }

    @GetMapping("/get-menu-parent")
    public ResponseEntity<List<Menu>> getMenuParent() {
        List<Menu> menus = menuRepo.findAll();
        return ResponseEntity.ok(menus);
    }

    @PostMapping(path = "/save-menu")
    public ResponseEntity<ReturnObject> saveMenu(@RequestBody Menu menu) {
        menuService.save(menu);
        ro.setData(menu);
        return ResponseEntity.ok(ro);
    }

    @PostMapping(path = "/delete-menu")
    public ResponseEntity<ReturnObject> deleteMenu(@RequestBody Menu menu) {
        menuRepo.delete(menu);
        return ResponseEntity.ok(ro);
    }

    @GetMapping("/get-report")
    public ResponseEntity<List<VRoleMenu>> getReport(@RequestParam String roleCode) {
        return ResponseEntity.ok(vRoleMenuRepo.getReport(roleCode));
    }

    @GetMapping("/get-role")
    public ResponseEntity<List<AppRole>> getRole() {
        return ResponseEntity.ok(appRoleRepo.findAll());
    }

    @PostMapping(path = "/save-role")
    public ResponseEntity<AppRole> saveRole(@RequestBody AppRole role) {
        role = roleService.save(role);
        return ResponseEntity.ok(role);
    }

    @PostMapping(path = "/save-privilege-menu")
    public ResponseEntity<ReturnObject> savePM(@RequestBody PrivilegeMenu pm) {
        privilegeMenuRepo.save(pm);
        return ResponseEntity.ok(ro);
    }

    @GetMapping(path = "/get-role-property")
    public ResponseEntity<List<RoleProperty>> getRoleProperty(@RequestParam String roleCode) {
        List<RoleProperty> property = rolePropertyRepo.getRoleProperty(roleCode);
        return ResponseEntity.ok(property);
    }

    @GetMapping(path = "/get-role-company")
    public ResponseEntity<List<VRoleCompany>> getRoleCompany(@RequestParam String roleCode) {
        List<VRoleCompany> property = vRoleCompanyRepo.getCompany(roleCode);
        return ResponseEntity.ok(property);
    }

    @GetMapping(path = "/get-privilege-role-company")
    public ResponseEntity<List<VRoleCompany>> getPRoleCompany(@RequestParam String roleCode) {
        List<VRoleCompany> property = vRoleCompanyRepo.getCompany(roleCode);
        property.removeIf(m -> !m.isAllow());
        return ResponseEntity.ok(property);
    }

    @PostMapping(path = "/save-role-property")
    public ResponseEntity<RoleProperty> saveRoleProperty(@RequestBody RoleProperty rp) {
        rp = rolePropertyRepo.save(rp);
        return ResponseEntity.ok(rp);
    }

    @PostMapping(path = "/delete-role-property")
    public ResponseEntity<ReturnObject> deleteRoleProperty(@RequestBody RoleProperty rp) {
        rolePropertyRepo.delete(rp);
        return ResponseEntity.ok(ro);
    }

    @PostMapping("/save-currency")
    public ResponseEntity<Currency> saveCurrency(@RequestBody Currency currency) {
        currency = currencyRepo.save(currency);
        return ResponseEntity.ok(currency);
    }

    @GetMapping("/get-currency")
    public ResponseEntity<List<Currency>> getCurrency() {
        return ResponseEntity.ok(currencyRepo.findAll());
    }

    @GetMapping("/find-currency")
    public ResponseEntity<Currency> findCurrency(@RequestParam String curCode) {
        Optional<Currency> cur = currencyRepo.findById(curCode);
        return ResponseEntity.ok(cur.isEmpty() ? null : cur.get());
    }


    @PostMapping("/save-company")
    public ResponseEntity<CompanyInfo> saveCompany(@RequestBody CompanyInfo info) {
        return ResponseEntity.ok(companyInfoService.save(info));
    }

    @PostMapping("/save-system-property")
    public ResponseEntity<SystemProperty> saveSystemProperty(@RequestBody SystemProperty property) {
        property = systemPropertyRepo.save(property);
        return ResponseEntity.ok(property);
    }

    @PostMapping("/save-mac-property")
    public ResponseEntity<MachineProperty> saveMacProperty(@RequestBody MachineProperty property) {
        if (!Objects.isNull(property.getKey().getMacId()) && !Objects.isNull(property.getKey().getPropKey())) {
            property = macPropertyRepo.save(property);
        }
        return ResponseEntity.ok(property);
    }

    @GetMapping("/get-company")
    public ResponseEntity<List<CompanyInfo>> getCompany() {
        return ResponseEntity.ok(companyInfoRepo.findAll());
    }

    @GetMapping("/get-system-property")
    public ResponseEntity<List<SystemProperty>> getSystemProperty(@RequestParam String compCode) {
        return ResponseEntity.ok(systemPropertyRepo.getSystemProperty(compCode));
    }

    @PostMapping("/find-system-property")
    public ResponseEntity<?> findSysProperty(@RequestBody PropertyKey key) {
        Optional<SystemProperty> p = systemPropertyRepo.findById(key);
        return ResponseEntity.ok(p.orElse(null));
    }

    @GetMapping("/get-mac-property")
    public ResponseEntity<List<MachineProperty>> getMacProperty(@RequestParam Integer macId) {
        return ResponseEntity.ok(macPropertyRepo.getMacProperty(macId));
    }

    @GetMapping(path = "/get-property")
    public ResponseEntity<HashMap<String, String>> getProperty(@RequestParam String compCode, @RequestParam String roleCode, @RequestParam Integer macId) {
        HashMap<String, String> hm = new HashMap<>();
        List<SystemProperty> systemProperty = systemPropertyRepo.getSystemProperty(compCode);
        if (!systemProperty.isEmpty()) {
            for (SystemProperty p : systemProperty) {
                hm.put(p.getKey().getPropKey(), p.getPropValue());
            }
        }

        List<RoleProperty> roleProperty = rolePropertyRepo.getRoleProperty(roleCode);
        if (!roleProperty.isEmpty()) {
            for (RoleProperty rp : roleProperty) {
                hm.put(rp.getKey().getPropKey(), rp.getPropValue());
            }
        }
        List<MachineProperty> machineProperties = macPropertyRepo.getMacProperty(macId);
        if (!machineProperties.isEmpty()) {
            for (MachineProperty p : machineProperties) {
                hm.put(p.getKey().getPropKey(), p.getPropValue());
            }
        }
        return ResponseEntity.ok(hm);
    }

    private List<VRoleMenu> getRoleMenuTree(String roleCode) {
        List<VRoleMenu> roles = vRoleMenuRepo.getMenuChild(roleCode, "1");
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getRoleMenuChild(role);
            }
        }
        return roles;
    }

    private List<VRoleMenu> getMenu(String roleCode) {
        List<VRoleMenu> roles = vRoleMenuRepo.getMenu(roleCode, "1");
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getMenuChild(role);
            }
        }
        return roles;
    }

    private void getMenuChild(VRoleMenu parent) {
        List<VRoleMenu> roles = vRoleMenuRepo.getMenu(parent.getRoleCode(), parent.getMenuCode());
        parent.setChild(roles);
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getMenuChild(role);
            }
        }
    }

    private void getRoleMenuChild(VRoleMenu parent) {
        List<VRoleMenu> roles = vRoleMenuRepo.getMenuChild(parent.getRoleCode(), parent.getMenuCode());
        parent.setChild(roles);
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getRoleMenuChild(role);
            }
        }
    }

    private List<Menu> getMenuTree() {
        List<Menu> menus = menuRepo.getMenuChild("1");
        if (!menus.isEmpty()) {
            for (Menu m : menus) {
                getMenuChild(m);
            }
        }
        return menus;
    }

    private void getMenuChild(Menu parent) {
        List<Menu> menus = menuRepo.getMenuChild(parent.getMenuCode());
        parent.setChild(menus);
        if (!menus.isEmpty()) {
            for (Menu m : menus) {
                getMenuChild(m);
            }
        }
    }

    @GetMapping(path = "/get-department")
    public ResponseEntity<?> getDepartment() {
        return ResponseEntity.ok(departmentRepo.findAll());
    }
}
