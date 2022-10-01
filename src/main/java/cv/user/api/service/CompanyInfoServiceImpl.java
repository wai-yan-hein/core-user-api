package cv.user.api.service;

import cv.user.api.common.Util1;
import cv.user.api.entity.*;
import cv.user.api.repo.AppRoleRepo;
import cv.user.api.repo.CompanyInfoRepo;
import cv.user.api.repo.PrivilegeCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyInfoServiceImpl implements CompanyInfoService {
    @Autowired
    private CompanyInfoRepo infoRepo;
    @Autowired
    private PrivilegeCompanyRepo companyRepo;
    @Autowired
    private AppRoleRepo roleRepo;
    @Autowired
    private SeqService seqService;

    @Override
    public CompanyInfo save(CompanyInfo info) {
        if (Util1.isNullOrEmpty(info.getCompCode())) {
            String compCode = getCompCode();
            info.setCompCode(compCode);
            updateRole(compCode);
        }
        return infoRepo.save(info);
    }

    private String getCompCode() {
        String option = "Company";
        String period = Util1.toDateStr(Util1.getTodayDate(), "MMyy");
        int seqNo = seqService.getSeqNo(new SeqKey(option, period));
        return period + "-" + String.format("%0" + 3 + "d", seqNo);
    }

    private void updateRole(String compCode) {
        List<AppRole> roles = roleRepo.findAll();
        for (AppRole r : roles) {
            PCKey key = new PCKey();
            key.setRoleCode(r.getRoleCode());
            key.setCompanyInfo(new CompanyInfo(compCode));
            PrivilegeCompany pc = new PrivilegeCompany();
            pc.setPcKey(key);
            pc.setAllow(false);
            companyRepo.save(pc);
        }
    }
}
