package cv.user.api.service;

import cv.user.api.common.Util1;
import cv.user.api.dao.DateLockDao;
import cv.user.api.entity.DateLock;
import cv.user.api.entity.SeqKey;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class DateLockServiceImpl implements DateLockService {
    private final DateLockDao dao;
    private final SeqService seqService;

    @Override
    public DateLock save(DateLock dl) {
        dl.setUpdatedDate(LocalDateTime.now());
        if (Util1.isNullOrEmpty(dl.getKey().getLockCode())) {
            dl.getKey().setLockCode(getCode());
        }
        return dao.save(dl);
    }


    @Override
    public List<DateLock> findAll(String compCode) {
        return dao.findAll(compCode);
    }

    private String getCode() {
        String option = "DateLock";
        String period = Util1.toDateStr(Util1.getTodayDate(), "MMyy");
        int seqNo = seqService.getSeqNo(new SeqKey(option, period));
        return period + "-" + String.format("%0" + 3 + "d", seqNo);
    }

}
