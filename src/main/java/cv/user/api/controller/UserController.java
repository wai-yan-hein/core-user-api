package cv.user.api.controller;

import cv.user.api.common.UserFilter;
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
    private DateLockRepo dateLockRepo;
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
    @Autowired
    private CountryService countryService;
    @Autowired
    private DateLockService dateLockService;

    @GetMapping("/hello")
    public Mono<?> hello() {
        return Mono.just("Hello.");
    }


    @GetMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestParam String userName,
                                         @RequestParam String password) {
        List<AppUser> list = userRepo.login(userName, password);
        if (list.isEmpty()) {
            return Mono.just(ResponseEntity.notFound().build());
        }
        return Mono.just(ResponseEntity.ok(list.get(0)));
    }

    @GetMapping("/checkSerialNo")
    public Mono<?> checkSerialNo(@RequestParam String serialNo) {
        Optional<MachineInfo> info = machineInfoRepo.findBySerialNo(serialNo);
        return info.isEmpty() ? Mono.empty() : Mono.just(info);
    }

    @GetMapping("/getMacList")
    public Flux<?> getMacList() {
        return Flux.fromIterable(machineInfoRepo.findAll()).onErrorResume((throwable -> Flux.empty()));
    }

    @DeleteMapping("/deleteMac")
    public Mono<?> deleteMac(@RequestParam Integer macId) {
        machineInfoRepo.deleteById(macId);
        return Mono.just(true);
    }

    @GetMapping("/updateAllMachine")
    public Mono<?> updateAllMachine(@RequestParam boolean update) {
        machineInfoRepo.updateAllMachine(update);
        return Mono.just(true);
    }


    @PostMapping("/saveMachine")
    public Mono<?> saveMachine(@RequestBody MachineInfo machineInfo) {
        machineInfo.setSerialNo(Util1.cleanStr(machineInfo.getSerialNo()));
        machineInfo.setUpdatedDate(LocalDateTime.now());
        return Mono.just(machineInfoRepo.save(machineInfo));
    }
    @PostMapping("/saveUser")
    public Mono<AppUser> saveUser(@RequestBody AppUser user) {
        return Mono.just(appUserService.save(user));
    }

    @PostMapping("/savePrivilegeCompany")
    public Mono<?> savePrivilegeCompany(@RequestBody PrivilegeCompany c) {
        return Mono.just(privilegeCompanyRepo.save(c));
    }

    @GetMapping("/getPrivilegeCompany")
    public Flux<?> getPC(@RequestParam String roleCode) {
        List<PrivilegeCompany> list = privilegeCompanyRepo.getRoleCompany(roleCode);
        list.forEach(p -> {
            Optional<CompanyInfo> c = companyInfoRepo.findById(p.getKey().getCompCode());
            c.ifPresent(info -> p.setCompName(info.getCompName()));
        });
        return Flux.fromIterable(list);
    }

    @GetMapping("/getAppUser")
    public Flux<?> getAppUser() {
        return Flux.fromIterable(userRepo.findAll()).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/findAppUser")
    public Mono<?> findAppUser(@RequestParam String userCode) {
        Optional<AppUser> byId = userRepo.findById(userCode);
        return byId.isPresent() ? Mono.just(byId.get()) : Mono.empty();
    }

    @GetMapping("/getRoleMenuTree")
    public Flux<?> getRoleMenu(@RequestParam String roleCode, @RequestParam String compCode) {
        return Flux.fromIterable(getMenu(roleCode, compCode)).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getPrivilegeRoleMenuTree")
    public Flux<?> getPrivilegeRoleMenuTree(@RequestParam String roleCode, @RequestParam String compCode) {
        List<VRoleMenu> menus = getRoleMenuTree(roleCode, compCode);
        return Flux.fromIterable(menus);
    }

    @GetMapping("/getMenuTree")
    public Flux<?> getMenuFlux(@RequestParam String compCode) {
        return Flux.fromIterable(getMenuTree(compCode));
    }

    @GetMapping("/getMenuParent")
    public Flux<?> getMenuParent(@RequestParam String compCode) {
        List<Menu> menus = menuRepo.getMenuDynamic(compCode);
        return Flux.fromIterable(menus).onErrorResume(throwable -> Flux.empty());
    }

    @PostMapping(path = "/saveMenu")
    public Mono<?> saveMenu(@RequestBody Menu menu) {
        return Mono.justOrEmpty(menuService.save(menu));
    }

    @PostMapping(path = "/deleteMenu")
    public Mono<?> deleteMenu(@RequestBody Menu menu) {
        menuRepo.delete(menu);
        return Mono.just(true);
    }

    @GetMapping("/getReport")
    public Flux<?> getReport(@RequestParam String roleCode, @RequestParam String menuClass, @RequestParam String compCode) {
        return Flux.fromIterable(vRoleMenuService.getReport(roleCode, Util1.isNull(menuClass, "-"), compCode)).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getRole")
    public Flux<?> getRole() {
        return Flux.fromIterable(appRoleRepo.findAll()).onErrorResume(throwable -> Flux.empty());
    }

    @PostMapping(path = "/saveRole")
    public Mono<?> saveRole(@RequestBody AppRole role) {
        return Mono.just(roleService.save(role));
    }

    @GetMapping(path = "/findCompany")
    public Mono<?> findCompany(@RequestParam String compCode) {
        return Mono.justOrEmpty(companyInfoRepo.findById(compCode).orElse(null));
    }

    @GetMapping(path = "/findRole")
    public Mono<?> findRole(@RequestParam String roleCode) {
        return Mono.justOrEmpty(roleService.findById(roleCode));
    }

    @PostMapping(path = "/savePrivilegeMenu")
    public Mono<?> savePM(@RequestBody PrivilegeMenu pm) {
        return Mono.justOrEmpty(privilegeMenuRepo.save(pm));
    }

    @GetMapping(path = "/getRoleProperty")
    public Flux<?> getRoleProperty(@RequestParam String roleCode, @RequestParam String compCode) {
        return Flux.fromIterable(rolePropertyRepo.getRoleProperty(roleCode, compCode));
    }

    @GetMapping(path = "/getRoleCompany")
    public Flux<?> getRoleCompany(@RequestParam String roleCode) {
        return Flux.fromIterable(vRoleCompanyService.getPrivilegeCompany(roleCode));
    }

    @GetMapping(path = "/getPrivilegeRoleCompany")
    public Flux<?> getPrivilegeRoleCompany(@RequestParam String roleCode) {
        return Flux.fromIterable(vRoleCompanyService.getPrivilegeCompany(roleCode)).onErrorResume(throwable -> Flux.empty());
    }

    @PostMapping(path = "/saveRoleProperty")
    public Mono<?> saveRoleProperty(@RequestBody RoleProperty rp) {
        return Mono.justOrEmpty(rolePropertyRepo.save(rp));
    }

    @PostMapping(path = "/deleteRoleProperty")
    public Mono<?> deleteRoleProperty(@RequestBody RoleProperty rp) {
        rolePropertyRepo.delete(rp);
        return Mono.just(true);
    }

    @PostMapping("/saveCurrency")
    public Mono<Currency> saveCurrency(@RequestBody Currency currency) {
        return Mono.justOrEmpty(currencyRepo.save(currency));
    }

    @GetMapping("/getCurrency")
    public Flux<?> getCurrency() {
        return Flux.fromIterable(currencyRepo.findAll()).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/findCurrency")
    public Mono<?> findCurrency(@RequestParam String curCode) {
        Optional<Currency> cur = currencyRepo.findById(curCode);
        return Mono.justOrEmpty(cur);
    }

    @PostMapping("/saveCompany")
    public Mono<?> saveCompany(@RequestBody CompanyInfo info) {
        return Mono.justOrEmpty(companyInfoService.save(info));
    }

    @PostMapping("/saveSystemProperty")
    public Mono<?> saveSystemProperty(@RequestBody SystemProperty property) {
        return Mono.justOrEmpty(systemPropertyRepo.save(property));
    }

    @PostMapping("/saveMacProperty")
    public Mono<?> saveMacProperty(@RequestBody MachineProperty property) {
        if (!Objects.isNull(property.getKey().getMacId()) && !Objects.isNull(property.getKey().getPropKey())) {
            property = macPropertyRepo.save(property);
        }
        return Mono.justOrEmpty(property);
    }

    @GetMapping("/getCompany")
    public Flux<?> getCompany(@RequestParam Boolean active) {
        return Flux.fromIterable(companyInfoRepo.findAll());
    }

    @GetMapping("/getSystemProperty")
    public Flux<?> getSystemProperty(@RequestParam String compCode) {
        return Flux.fromIterable(systemPropertyRepo.getSystemProperty(compCode));
    }

    @GetMapping("/getMacProperty")
    public Flux<?> getMacProperty(@RequestParam Integer macId) {
        return Flux.fromIterable(macPropertyRepo.getMacProperty(macId));
    }

    @GetMapping(path = "/getProperty")
    public Mono<?> getProperty(@RequestParam String compCode, @RequestParam String roleCode, @RequestParam Integer macId) {
        HashMap<String, String> hm = new HashMap<>();
        List<SystemProperty> systemProperty = systemPropertyRepo.getSystemProperty(compCode);
        if (!systemProperty.isEmpty()) {
            for (SystemProperty p : systemProperty) {
                hm.put(p.getKey().getPropKey(), p.getPropValue());
            }
        }
        List<RoleProperty> roleProperty = rolePropertyRepo.getRoleProperty(roleCode, compCode);
        if (!roleProperty.isEmpty()) {
            for (RoleProperty rp : roleProperty) {
                String value = rp.getPropValue();
                if (!Util1.isNullOrEmpty(value) || !value.equals("-")) {
                    hm.put(rp.getKey().getPropKey(), value);
                }
            }
        }
        List<MachineProperty> machineProperties = macPropertyRepo.getMacProperty(macId);
        if (!machineProperties.isEmpty()) {
            for (MachineProperty p : machineProperties) {
                String value = p.getPropValue();
                if (!Util1.isNullOrEmpty(value) || !value.equals("-")) {
                    hm.put(p.getKey().getPropKey(), value);
                }
            }
        }
        return Mono.just(hm);
    }

    private List<VRoleMenu> getRoleMenuTree(String roleCode, String compCode) {
        List<VRoleMenu> roles = vRoleMenuService.getMenu(roleCode, "#", compCode, true);
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getRoleMenuChild(role, true);
            }
        }
        return roles;
    }

    private List<VRoleMenu> getMenu(String roleCode, String compCode) {
        List<VRoleMenu> roles = vRoleMenuService.getMenu(roleCode, "#", compCode, false);
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getMenuChild(role, false);
            }
        }
        return roles;
    }

    private void getMenuChild(VRoleMenu parent, boolean privilege) {
        List<VRoleMenu> roles = vRoleMenuService.getMenu(parent.getRoleCode(), parent.getMenuCode(), parent.getCompCode(), privilege);
        parent.setChild(roles);
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getMenuChild(role, privilege);
            }
        }
    }

    private void getRoleMenuChild(VRoleMenu parent, boolean privilege) {
        List<VRoleMenu> roles = vRoleMenuService.getMenu(parent.getRoleCode(), parent.getMenuCode(), parent.getCompCode(), privilege);
        parent.setChild(roles);
        if (!roles.isEmpty()) {
            for (VRoleMenu role : roles) {
                getRoleMenuChild(role, privilege);
            }
        }
    }

    private List<Menu> getMenuTree(String compCode) {
        List<Menu> menus = menuRepo.getMenuChild("#", compCode);
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

    @GetMapping(path = "/getDepartment")
    public Flux<?> getDepartment(@RequestParam Boolean active,
                                 @RequestParam String compCode) {
        if (active) {
            return Flux.fromIterable(departmentRepo.getDepartment(true, compCode));
        }
        return Flux.fromIterable(departmentRepo.getDepartment(compCode));
    }

    @PostMapping("/findDepartment")
    public Mono<Department> findDepartment(@RequestBody DepartmentKey key) {
        return departmentRepo.findById(key)
                .map(Mono::just)
                .orElseGet(Mono::empty);
    }

    @PostMapping("/saveDepartment")
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
        return Flux.fromIterable(userRepo.getUserByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getBusinessTypeByDate")
    public Flux<?> getBusinessTypeByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(businessTypeRepo.getBusinessTypeByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getCompanyInfoByDate")
    public Flux<?> getCompanyInfoByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(companyInfoRepo.getCompanyInfoByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getCurrencyByDate")
    public Flux<?> getCurrencyByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(currencyRepo.getCurrencyByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getDepartmentByDate")
    public Flux<?> getDepartmentByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(departmentRepo.getDepartmentByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getExchangeRateByDate")
    public Flux<?> getExchangeRateByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(exchangeRateRepo.getExchangeRateByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getMacPropertyByDate")
    public Flux<?> getMacPropertyByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(macPropertyRepo.getMacPropertyByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getMachineInfoByDate")
    public Flux<?> getMachineInfoByDate(@RequestParam String updatedDate) {
log.info(updatedDate);
        return Flux.fromIterable(machineInfoRepo.getMachineInfoByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getMenuByDate")
    public Flux<?> getMenuByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(menuRepo.getMenuByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getPCByDate")
    public Flux<?> getPCByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(privilegeCompanyRepo.getPCByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getPMByDate")
    public Flux<?> getPMByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(privilegeMenuRepo.getPMByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getProjectByDate")
    public Flux<?> getProjectByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(projectRepo.getProjectByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getRoleByDate")
    public Flux<?> getRoleByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(appRoleRepo.getRoleByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getRolePropByDate")
    public Flux<?> getRolePropByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(rolePropertyRepo.getRolePropByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getSeqTableByDate")
    public Flux<?> getSeqTableByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(seqRepo.getSeqTableByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getSystemPropertyByDate")
    public Flux<?> getSystemPropertyByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(systemPropertyRepo.getSystemPropertyByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getDateLockByDate")
    public Flux<?> getDateLockByDate(@RequestParam String updatedDate) {
        return Flux.fromIterable(dateLockRepo.getDateLockByDate(Util1.toLocalDateTime(updatedDate))).onErrorResume(throwable -> Flux.empty());
    }

    @GetMapping("/getExchangeRate")
    public Flux<?> getExchangeRate(@RequestParam String compCode) {
        return Flux.fromIterable(exchangeRateService.getExchangeRate(compCode)).onErrorResume((e) -> Flux.empty());
    }

    @PostMapping("/findExchange")
    public Mono<?> findExchange(@RequestBody ExchangeKey key) {
        return Mono.justOrEmpty(exchangeRateService.findById(key));
    }

    @GetMapping("/getCountry")
    public Flux<?> getCountry() {
        return Flux.fromIterable(countryService.getCountry()).onErrorResume((e) -> Flux.empty());
    }

    @GetMapping("/findCountry")
    public Mono<Country> findCountry(@RequestParam String id) {
        return Mono.justOrEmpty(countryService.findCountry(id)).onErrorResume((e) -> Mono.empty());
    }

    @GetMapping("/getDateLock")
    public Flux<?> getDateLock(@RequestParam String compCode) {
        return Flux.fromIterable(dateLockService.findAll(compCode)).map(dateLock -> {
            dateLock.setCreatedDateTime(Util1.toZonedDateTime(dateLock.getCreatedDate()));
            dateLock.setUpdatedDateTime(Util1.toZonedDateTime(dateLock.getUpdatedDate()));
            return dateLock;
        }).onErrorResume(throwable -> Flux.empty());
    }

    @PostMapping("/saveDateLock")
    public Mono<?> saveDateLock(@RequestBody DateLock dl) {
        return Mono.just(dateLockService.save(dl));
    }
}
