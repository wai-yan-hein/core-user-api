package cv.user.api.repo;

import cv.user.api.entity.PropertyKey;
import cv.user.api.entity.SeqTable;
import cv.user.api.entity.SystemProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface SystemPropertyRepo extends JpaRepository<SystemProperty, PropertyKey> {
    @Query("select o from SystemProperty o where o.key.compCode =:compCode or '-' = :compCode ")
    List<SystemProperty> getSystemProperty(@Param("compCode") String compCode);

    @Query("select o from SystemProperty o where o.updatedDate > :updatedDate")
    List<SystemProperty> getSystemPropertyByDate(@Param("updatedDate") Timestamp updatedDate);
}
