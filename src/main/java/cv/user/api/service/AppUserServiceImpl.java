package cv.user.api.service;

import cv.user.api.common.Util1;
import cv.user.api.entity.AppUser;
import cv.user.api.entity.SeqKey;
import cv.user.api.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private SeqService seqService;

    @Override
    public AppUser save(AppUser user) {
        if (Util1.isNullOrEmpty(user.getUserCode())) {
            user.setUserCode(getUserCode());
        }
        return appUserRepo.save(user);
    }

    private String getUserCode() {
        String option = "AppUser";
        String period = Util1.toDateStr(Util1.getTodayDate(), "MMyy");
        int seqNo = seqService.getSeqNo(new SeqKey(option, period));
        return period + "-" + String.format("%0" + 3 + "d", seqNo);
    }
}
