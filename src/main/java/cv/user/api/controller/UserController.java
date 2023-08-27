package cv.user.api.controller;

import cv.user.api.common.*;
import cv.user.api.entity.*;
import cv.user.api.repo.*;
import cv.user.api.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
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
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/hello")
    public Mono<?> hello() {
        return Mono.just("Hello.");
    }


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String userName,
                                   @RequestParam String password) {
        List<AppUser> list = userRepo.login(userName, password);
        return ResponseEntity.ok(list.isEmpty() ? null : list.get(0));
    }

    @GetMapping("/checkSerialNo")
    public Mono<?> checkSerialNo(@RequestParam String serialNo) {
        Optional<MachineInfo> info = machineInfoRepo.findBySerialNo(serialNo);
        return info.isEmpty() ? Mono.empty() : Mono.just(info);
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
    public Flux<?> getRoleProperty(@RequestParam String roleCode) {
        return Flux.fromIterable(rolePropertyRepo.getRoleProperty(roleCode));
    }

    @GetMapping(path = "/get-role-company")
    public Flux<?> getRoleCompany(@RequestParam String roleCode) {
        return Flux.fromIterable(vRoleCompanyService.getPrivilegeCompany(roleCode));
    }

    @GetMapping(path = "/get-privilege-role-company")
    public Flux<?> getPRoleCompany(@RequestParam String roleCode) {
        return Flux.fromIterable(vRoleCompanyService.getPrivilegeCompany(roleCode));
    }

    @PostMapping(path = "/save-role-property")
    public Mono<?> saveRoleProperty(@RequestBody RoleProperty rp) {
        return Mono.justOrEmpty(rolePropertyRepo.save(rp));
    }

    @PostMapping(path = "/delete-role-property")
    public Mono<?> deleteRoleProperty(@RequestBody RoleProperty rp) {
        rolePropertyRepo.delete(rp);
        return Mono.just(true);
    }

    @PostMapping("/save-currency")
    public Mono<Currency> saveCurrency(@RequestBody Currency currency) {
        return Mono.justOrEmpty(currencyRepo.save(currency));
    }

    @GetMapping("/get-currency")
    public Flux<?> getCurrency() {
        return Flux.fromIterable(currencyRepo.findAll());
    }

    @GetMapping("/find-currency")
    public Mono<?> findCurrency(@RequestParam String curCode) {
        Optional<Currency> cur = currencyRepo.findById(curCode);
        return Mono.justOrEmpty(cur);
    }


    @PostMapping("/save-company")
    public Mono<?> saveCompany(@RequestBody CompanyInfo info) {
        return Mono.justOrEmpty(companyInfoService.save(info));
    }

    @PostMapping("/save-system-property")
    public Mono<?> saveSystemProperty(@RequestBody SystemProperty property) {
        return Mono.justOrEmpty(systemPropertyRepo.save(property));
    }

    @PostMapping("/save-mac-property")
    public Mono<?> saveMacProperty(@RequestBody MachineProperty property) {
        if (!Objects.isNull(property.getKey().getMacId()) && !Objects.isNull(property.getKey().getPropKey())) {
            property = macPropertyRepo.save(property);
        }
        return Mono.justOrEmpty(property);
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
    public Mono<?> findSysProperty(@RequestBody PropertyKey key) {
        Optional<SystemProperty> p = systemPropertyRepo.findById(key);
        return Mono.justOrEmpty(p.orElse(null));
    }

    @GetMapping("/get-mac-property")
    public Flux<?> getMacProperty(@RequestParam Integer macId) {
        return Flux.fromIterable(macPropertyRepo.getMacProperty(macId));
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
    public Flux<?> getDepartment(@RequestParam Boolean active) {
        if (active) {
            return Flux.fromIterable(departmentRepo.getDepartment(true));
        }
        return Flux.fromIterable(departmentRepo.getDepartment());
    }

    @GetMapping("/find-department")
    public Mono<Department> findDepartment(@RequestParam Integer deptId) {
        Optional<Department> departmentOptional = departmentRepo.findById(deptId);
        return departmentOptional
                .map(Mono::just)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
    }

    @PostMapping("/save-department")
    public Mono<?> saveDepartment(@RequestBody Department department) {
        return Mono.justOrEmpty(departmentService.save(department));
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
        rate.setCreatedDate(LocalDateTime.now());
        return Mono.justOrEmpty(exchangeRateService.save(rate));
    }

    @PostMapping(path = "/deleteExchange")
    public Mono<?> saveExchange(@RequestBody ExchangeKey key) {
        return Mono.justOrEmpty(exchangeRateService.delete(key));
    }

    @GetMapping(path = "/searchExchange")
    public Flux<?> searchExchange(@RequestParam String startDate,
                                  @RequestParam String endDate,
                                  @RequestParam String targetCur,
                                  @RequestParam String compCode) {
        List<ExchangeRate> list = exchangeRateService.search(startDate, endDate, targetCur, compCode);
        list.forEach((t) -> t.setExRate(t.getHomeFactor() / t.getTargetFactor()));
        return Flux.fromIterable(list).onErrorResume((t) -> Flux.empty());
    }

    @PostMapping(path = "/getExchangeAvg")
    public Mono<?> getExchangeAvg(@RequestBody UserFilter filter) {
        return Mono.justOrEmpty(exchangeRateService.getAvgRate(filter));
    }
    @PostMapping(path = "/getExchangeRecent")
    public Mono<?> getExchangeRecent(@RequestBody UserFilter filter) {
        return Mono.justOrEmpty(exchangeRateService.getRecentRate(filter));
    }

    @PostMapping(path = "/yearEnd")
    public Mono<?> yearEnd(@RequestBody YearEnd end) {
        return Mono.justOrEmpty(companyInfoService.yearEnd(end));
    }

    @GetMapping("/getUserByDate")
    public Flux<?> getUserByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(userRepo.getUserByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getBusinessTypeByDate")
    public Flux<?> getBusinessTypeByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(businessTypeRepo.getBusinessTypeByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getCompanyInfoByDate")
    public Flux<?> getCompanyInfoByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(companyInfoRepo.getCompanyInfoByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getCurrencyByDate")
    public Flux<?> getCurrencyByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(currencyRepo.getCurrencyByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getDepartmentByDate")
    public Flux<?> getDepartmentByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(departmentRepo.getDepartmentByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getExchangeRateByDate")
    public Flux<?> getExchangeRateByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(exchangeRateRepo.getExchangeRateByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getMacPropertyByDate")
    public Flux<?> getMacPropertyByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(macPropertyRepo.getMacPropertyByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getMachineInfoByDate")
    public Flux<?> getMachineInfoByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(machineInfoRepo.getMachineInfoByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getMenuByDate")
    public Flux<?> getMenuByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(menuRepo.getMenuByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getPCByDate")
    public Flux<?> getPCByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(privilegeCompanyRepo.getPCByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getPMByDate")
    public Flux<?> getPMByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(privilegeMenuRepo.getPMByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getProjectByDate")
    public Flux<?> getProjectByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(projectRepo.getProjectByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getRoleByDate")
    public Flux<?> getRoleByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(appRoleRepo.getRoleByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getRolePropByDate")
    public Flux<?> getRolePropByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(rolePropertyRepo.getRolePropByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getSeqTableByDate")
    public Flux<?> getSeqTableByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(seqRepo.getSeqTableByDate(Util1.toLocalDateTime(updatedDate)));
    }

    @GetMapping("/getSystemPropertyByDate")
    public Flux<?> getSystemPropertyByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(systemPropertyRepo.getSystemPropertyByDate(Util1.toLocalDateTime(updatedDate)));
    }
}
