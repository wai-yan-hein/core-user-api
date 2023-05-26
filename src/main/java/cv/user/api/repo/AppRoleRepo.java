package cv.user.api.repo;

import cv.user.api.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AppRoleRepo extends JpaRepository<AppRole, String> {
    @Query("select o from AppRole o where o.updatedDate > :updatedDate")
    List<AppRole> getRoleByDate(@Param("updatedDate") Timestamp updatedDate);
}
