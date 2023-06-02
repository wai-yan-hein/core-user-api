package cv.user.api.controller;

import cv.user.api.common.ReturnObject;
import cv.user.api.common.Util1;
import cv.user.api.common.YearEnd;
import cv.user.api.entity.*;
import cv.user.api.repo.*;
import cv.user.api.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
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
    private BusinessTypeRepo businessTypeRepo;
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
    @Autowired
    private ExchangeRateRepo exchangeRateRepo;
    @Autowired
    private BusinessTypeService businessTypeService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private SeqRepo seqRepo;
    @Autowired
    private VRoleCompanyService vRoleCompanyService;
    @Autowired
    private ExchangeRateService exchangeRateService;
    @Autowired
    private VRoleMenuService vRoleMenuService;
    private final ReturnObject ro = new ReturnObject();

    @GetMapping("/hello")
    public String hello() {
        return "Hello Back.";
    }

    @GetMapping("/login")
    public Mono<?> login(@RequestParam String userName, @RequestParam String password) {
        List<AppUser> list = userRepo.login(userName, password);
        return Mono.justOrEmpty(list.isEmpty() ? null : list.get(0));
    }

    @GetMapping("/get-mac-info")
    public Mono<MachineInfo> getMacInfo(@RequestParam String macName) {
        MachineInfo mac = new MachineInfo();
        mac.setMacId(0);
        List<MachineInfo> byName = machineInfoRepo.findByName(macName);
        if (!byName.isEmpty()) {
            mac = byName.get(0);
        }
        return Mono.justOrEmpty(mac);
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
    public Mono<?> saveMacInfo(@RequestBody MachineInfo machineInfo) {
        return Mono.justOrEmpty(machineInfoRepo.save(machineInfo));
    }

    @PostMapping("/save-user")
    public Mono<AppUser> saveUser(@RequestBody AppUser user) {
        return Mono.just(appUserService.save(user));
    }

    @PostMapping("/save-privilege-company")
    public ResponseEntity<PrivilegeCompany> savePC(@RequestBody PrivilegeCompany c) {
        return ResponseEntity.ok(privilegeCompanyRepo.save(c));
    }

    @GetMapping("/get-privilege-company")
    public Flux<?> getPC(@RequestParam String roleCode) {
        List<PrivilegeCompany> list = privilegeCompanyRepo.getRoleCompany(roleCode);
        list.forEach(p -> {
            Optional<CompanyInfo> c = companyInfoRepo.findById(p.getKey().getCompCode());
            c.ifPresent(info -> p.setCompName(info.getCompName()));
        });
        return Flux.fromIterable(list);
    }

    @GetMapping("/get-appuser")
    public Flux<?> getAppUser() {
        return Flux.fromIterable(userRepo.findAll());
    }

    @GetMapping("/find-appuser")
    public ResponseEntity<AppUser> findAppUser(@RequestParam String userCode) {
        Optional<AppUser> byId = userRepo.findById(userCode);
        return ResponseEntity.ok(byId.orElse(null));
    }

    @GetMapping("/get-role-menu-tree")
    public ResponseEntity<List<VRoleMenu>> getRoleMenu(@RequestParam String roleCode, @RequestParam String compCode) {
        return ResponseEntity.ok(getMenu(roleCode, compCode));
    }

    @GetMapping("/get-privilege-role-menu-tree")
    public Flux<?> getPRoleMenu(@RequestParam String roleCode, @RequestParam String compCode) {
        List<VRoleMenu> menus = getRoleMenuTree(roleCode, compCode);
        menus.removeIf(m -> !m.isAllow());
        return Flux.fromIterable(menus);
    }

    @GetMapping("/get-menu-tree")
    public Flux<?> getMenuFlux(@RequestParam String compCode) {
        return Flux.fromIterable(getMenuTree(compCode));
    }

    @GetMapping("/get-menu-parent")
    public ResponseEntity<List<Menu>> getMenuParent(@RequestParam String compCode) {
        List<Menu> menus = menuRepo.getMenuDynamic(compCode);
        return ResponseEntity.ok(menus);
    }

    @PostMapping(path = "/save-menu")
    public Mono<?> saveMenu(@RequestBody Menu menu) {
        return Mono.justOrEmpty(menuService.save(menu));
    }

    @PostMapping(path = "/delete-menu")
    public Mono<?> deleteMenu(@RequestBody Menu menu) {
        menuRepo.delete(menu);
        return Mono.just(true);
    }

    @GetMapping("/get-report")
    public ResponseEntity<List<VRoleMenu>> getReport(@RequestParam String roleCode, @RequestParam String menuClass, @RequestParam String compCode) {
        return ResponseEntity.ok(vRoleMenuService.getReport(roleCode, Util1.isNull(menuClass, "-"), compCode));
    }

    @GetMapping("/get-role")
    public ResponseEntity<List<AppRole>> getRole() {
        return ResponseEntity.ok(appRoleRepo.findAll());
    }

    @PostMapping(path = "/save-role")
    public Mono<?> saveRole(@RequestBody AppRole role) {
        return Mono.just(roleService.save(role));
    }

    @GetMapping(path = "/findCompany")
    public Mono<?> findCompany(@RequestParam String compCode) {
        return Mono.justOrEmpty(companyInfoRepo.findById(compCode).orElse(null));
    }

    @GetMapping(path = "/find-role")
    public Mono<?> findRole(@RequestParam String roleCode) {
        return Mono.justOrEmpty(roleService.findById(roleCode));
    }

    @PostMapping(path = "/save-privilege-menu")
    public Mono<?> savePM(@RequestBody PrivilegeMenu pm) {
        return Mono.justOrEmpty(privilegeMenuRepo.save(pm));
    }

    @GetMapping(path = "/get-role-property")
    public ResponseEntity<List<RoleProperty>> getRoleProperty(@RequestParam String roleCode) {
        List<RoleProperty> property = rolePropertyRepo.getRoleProperty(roleCode);
        return ResponseEntity.ok(property);
    }

    @GetMapping(path = "/get-role-company")
    public ResponseEntity<List<VRoleCompany>> getRoleCompany(@RequestParam String roleCode) {
        List<VRoleCompany> property = vRoleCompanyService.getPrivilegeCompany(roleCode);
        return ResponseEntity.ok(property);
    }

    @GetMapping(path = "/get-privilege-role-company")
    public Flux<?> getPRoleCompany(@RequestParam String roleCode) {
        return Flux.fromIterable(vRoleCompanyService.getPrivilegeCompany(roleCode));
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
        return ResponseEntity.ok(cur.orElse(null));
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
    public Flux<?> getCompany(@RequestParam Boolean active) {
        return Flux.fromIterable(companyInfoRepo.findAll());
    }

    @GetMapping("/get-system-property")
    public Flux<?> getSystemProperty(@RequestParam String compCode) {
        return Flux.fromIterable(systemPropertyRepo.getSystemProperty(compCode));
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
    public Mono<?> getProperty(@RequestParam String compCode, @RequestParam String roleCode, @RequestParam Integer macId) {
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
        return Mono.just(hm);
    }

    private List<VRoleMenu> getRoleMenuTree(String roleCode, String compCode) {
        List<VRoleMenu> roles = vRoleMenuService.getMenuChild(roleCode, "1", compCode);
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getRoleMenuChild(role);
            }
        }
        return roles;
    }

    private List<VRoleMenu> getMenu(String roleCode, String compCode) {
        List<VRoleMenu> roles = vRoleMenuService.getMenu(roleCode, "1", compCode);
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getMenuChild(role);
            }
        }
        return roles;
    }

    private void getMenuChild(VRoleMenu parent) {
        List<VRoleMenu> roles = vRoleMenuService.getMenu(parent.getRoleCode(), parent.getMenuCode(), parent.getCompCode());
        parent.setChild(roles);
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getMenuChild(role);
            }
        }
    }

    private void getRoleMenuChild(VRoleMenu parent) {
        List<VRoleMenu> roles = vRoleMenuService.getMenuChild(parent.getRoleCode(), parent.getMenuCode(), parent.getCompCode());
        parent.setChild(roles);
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getRoleMenuChild(role);
            }
        }
    }

    private List<Menu> getMenuTree(String compCode) {
        List<Menu> menus = menuRepo.getMenuChild("1", compCode);
        if (!menus.isEmpty()) {
            for (Menu m : menus) {
                getMenuChild(m);
            }
        }
        return menus;
    }

    private void getMenuChild(Menu parent) {
        List<Menu> menus = menuRepo.getMenuChild(parent.getKey().getMenuCode(), parent.getKey().getCompCode());
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

    @GetMapping("/find-department")
    public ResponseEntity<Department> findDepartment(@RequestParam Integer deptId) {
        Optional<Department> byId = departmentRepo.findById(deptId);
        return ResponseEntity.ok(byId.orElse(null));
    }

    @PostMapping("/save-department")
    public ResponseEntity<?> saveDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentRepo.save(department));
    }

    @GetMapping(path = "/getBusinessType")
    public Flux<?> getBusinessType() {
        return Flux.fromIterable(businessTypeService.findAll());
    }

    @GetMapping(path = "/findBusinessType")
    public Mono<?> findBusinessType(@RequestParam Integer id) {
        return Mono.justOrEmpty(businessTypeService.findById(id));
    }

    @PostMapping(path = "/saveBusinessType")
    public Mono<?> saveBusinessType(@RequestBody BusinessType type) {
        return Mono.justOrEmpty(businessTypeService.save(type));
    }

    @PostMapping(path = "/saveProject")
    public Mono<?> saveProject(@RequestBody Project project) {
        return Mono.justOrEmpty(projectService.save(project));
    }

    @PostMapping(path = "/findProject")
    public Mono<?> findProject(@RequestBody ProjectKey key) {
        return Mono.justOrEmpty(projectService.find(key));
    }

    @GetMapping(path = "/searchProject")
    public Flux<?> searchProject(@RequestParam String compCode) {
        return Flux.fromIterable(projectService.search(compCode));
    }

    @GetMapping(path = "/searchProjectByCode")
    public Flux<?> searchProjectByCode(@RequestParam String code, @RequestParam String compCode) {
        return Flux.fromIterable(projectService.search(code, compCode));
    }

    @PostMapping(path = "/saveExchange")
    public Mono<?> saveExchange(@RequestBody ExchangeRate rate) {
        rate.setExDate(Util1.toDateTime(rate.getExDate()));
        rate.setCreatedDate(Util1.toDateTime(rate.getCreatedDate()));
        return Mono.justOrEmpty(exchangeRateService.save(rate));
    }

    @PostMapping(path = "/deleteExchange")
    public Mono<?> saveExchange(@RequestBody ExchangeKey key) {
        return Mono.justOrEmpty(exchangeRateService.delete(key));
    }

    @GetMapping(path = "/searchExchange")
    public Flux<?> searchExchange(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String targetCur, @RequestParam String compCode) {
        List<ExchangeRate> list = exchangeRateService.search(startDate, endDate, targetCur, compCode);
        list.forEach((t) -> {
            t.setExRate(t.getHomeFactor() / t.getTargetFactor());
        });
        return Flux.fromIterable(list);
    }

    @PostMapping(path = "/yearEnd")
    public Mono<?> yearEnd(@RequestBody YearEnd end) {
        return Mono.justOrEmpty(companyInfoService.yearEnd(end));
    }

    @GetMapping("/getUserByDate")
    public Flux<?> getUserByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(userRepo.getUserByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getBusinessTypeByDate")
    public Flux<?> getBusinessTypeByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(businessTypeRepo.getBusinessTypeByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getCompanyInfoByDate")
    public Flux<?> getCompanyInfoByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(companyInfoRepo.getCompanyInfoByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getCurrencyByDate")
    public Flux<?> getCurrencyByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(currencyRepo.getCurrencyByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getDepartmentByDate")
    public Flux<?> getDepartmentByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(departmentRepo.getDepartmentByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getExchangeRateByDate")
    public Flux<?> getExchangeRateByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(exchangeRateRepo.getExchangeRateByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getMacPropertyByDate")
    public Flux<?> getMacPropertyByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(macPropertyRepo.getMacPropertyByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getMachineInfoByDate")
    public Flux<?> getMachineInfoByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(machineInfoRepo.getMachineInfoByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getMenuByDate")
    public Flux<?> getMenuByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(menuRepo.getMenuByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getPCByDate")
    public Flux<?> getPCByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(privilegeCompanyRepo.getPCByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getPMByDate")
    public Flux<?> getPMByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(privilegeMenuRepo.getPMByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getProjectByDate")
    public Flux<?> getProjectByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(projectRepo.getProjectByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getRoleByDate")
    public Flux<?> getRoleByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(appRoleRepo.getRoleByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getRolePropByDate")
    public Flux<?> getRolePropByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(rolePropertyRepo.getRolePropByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getSeqTableByDate")
    public Flux<?> getSeqTableByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(seqRepo.getSeqTableByDate(Timestamp.valueOf(updatedDate)));
    }

    @GetMapping("/getSystemPropertyByDate")
    public Flux<?> getSystemPropertyByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(systemPropertyRepo.getSystemPropertyByDate(Timestamp.valueOf(updatedDate)));
    }
}
