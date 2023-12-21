package cv.user.api.dao;

import cv.user.api.entity.Language;
import cv.user.api.entity.LanguageKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository
public class LanguageDaoImpl extends AbstractDao<LanguageKey, Language> implements LanguageDao{
    @Override
    public Language save(Language s) {
        s.setUpdatedDate(LocalDateTime.now());
        saveOrUpdate(s, s.getKey());
        return s;
    }

    @Override
    public List<Language> findAll(String compCode) {
        String hsql = "select o from Language o";
        return findHSQL(hsql);
    }

    @Override
    public int delete(LanguageKey key) {
        remove(key);
        return 1;
    }

    @Override
    public Language findById(LanguageKey id) {
        return getByKey(id);
    }

    @Override
    public List<Language> search(String des) {
        String strSql = "";

        if (!des.equals("-")) {
            strSql = "o.lanValue like '%" + des + "%'";
        }

        if (strSql.isEmpty()) {
            strSql = "select o from Language o";
        } else {
            strSql = "select o from Language o where " + strSql;
        }

        return findHSQL(strSql);
    }

    @Override
    public List<Language> unUpload() {
        String hsql = "select o from Language o where o.intgUpdStatus is null";
        return findHSQL(hsql);
    }

    @Override
    public Date getMaxDate() {
        String sql = "select max(updated_date) date from language";
        ResultSet rs = getResult(sql);
        try {
            if (rs.next()) {
                Date date = rs.getTimestamp("date");
                if (date != null) {
                    return date;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null; // Util1.getOldDate();
    }

    @Override
    public List<Language> getLanguage(LocalDateTime updatedDate) {
        String hsql = "select o from Language o where o.updatedDate > :updatedDate";
        return null; // createQuery(hsql).setParameter("updatedDate", updatedDate).getResultList();
    }
}
