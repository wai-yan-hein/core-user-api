package cv.user.api.dao;

import cv.user.api.entity.Language;
import cv.user.api.entity.LanguageKey;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface LanguageDao {
    Language save(Language language);

    List<Language> findAll(String compCode);

    int delete(LanguageKey key);

    Language findById(LanguageKey id);

    List<Language> search(String des);

    List<Language> unUpload();

    Date getMaxDate();

    List<Language> getLanguage(LocalDateTime updatedDate);
}
