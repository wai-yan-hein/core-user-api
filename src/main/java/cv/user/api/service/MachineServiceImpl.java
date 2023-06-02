package cv.user.api.service;

import cv.user.api.common.Util1;
import cv.user.api.entity.MachineInfo;
import cv.user.api.repo.MachineInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MachineServiceImpl implements MachineService{
    @Autowired
    private MachineInfoRepo repo;
    @Override
    public MachineInfo save(MachineInfo info) {
        if(Util1.isNullOrEmpty(info.getMacId())){
            info.setMacId(repo.findMaxId());
        }
        return repo.save(info);
    }
}
