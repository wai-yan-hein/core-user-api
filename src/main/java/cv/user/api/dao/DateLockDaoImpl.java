package cv.user.api.dao;

import cv.user.api.entity.DateLock;
import cv.user.api.entity.DateLockKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DateLockDaoImpl extends AbstractDao<DateLockKey, DateLock> implements DateLockDao {

    @Override
    public DateLock save(DateLock dl) {
        saveOrUpdate(dl, dl.getKey());
        return dl;
    }

    @Override
    public List<DateLock> findAll(String compCode) {
        String hsql = "select o from DateLock o where o.key.compCode ='" + compCode + "'";
        return findHSQL(hsql);
    }
}
