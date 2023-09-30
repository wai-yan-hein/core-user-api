package cv.user.api.service;

import cv.user.api.entity.DateLock;

import java.util.List;

public interface DateLockService {
    DateLock save(DateLock dl);
    List<DateLock> findAll(String compCode);
}
