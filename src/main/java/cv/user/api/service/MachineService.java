package cv.user.api.service;

import cv.user.api.entity.MachineInfo;

import java.util.Optional;

public interface MachineService {
    MachineInfo save(MachineInfo info);
    Optional<MachineInfo> findBySerialNo(String serialNo);

}
