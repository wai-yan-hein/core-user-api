package cv.user.api.repo;

import cv.user.api.entity.RoleProperty;
import cv.user.api.entity.RolePropertyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface RolePropertyRepo extends JpaRepository<RoleProperty, RolePropertyKey> {
    @Query("select o from RoleProperty o where o.key.roleCode = :roleCode")
    List<RoleProperty> getRoleProperty(@Param("roleCode") String roleCode);
    @Query("select o from RoleProperty o where o.key.compCode = :compCode")
    List<RoleProperty> getRole(@Param("compCode") String compCode);
    @Query("select o from RoleProperty o where o.updatedDate > :updatedDate")
    List<RoleProperty> getRolePropByDate(@Param("updatedDate") LocalDateTime updatedDate);
}
