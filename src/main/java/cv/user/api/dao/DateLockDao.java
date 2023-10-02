package cv.user.api.dao;

import cv.user.api.entity.DateLock;

import java.util.List;

public interface DateLockDao {
    DateLock save(DateLock dl); 
    List<DateLock> findAll(String compCode);
}
