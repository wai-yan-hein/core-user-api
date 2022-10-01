package cv.user.api.repo;

import cv.user.api.entity.SystemProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SystemPropertyRepo extends JpaRepository<SystemProperty, String> {
    @Query("select o from SystemProperty o where o.compCode =:compCode ")
    List<SystemProperty> getSystemProperty(@Param("compCode") String compCode);
}
