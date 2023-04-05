package cv.user.api.repo;

import cv.user.api.entity.PMKey;
import cv.user.api.entity.PrivilegeMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrivilegeMenuRepo extends JpaRepository<PrivilegeMenu, PMKey> {
    @Query("select o from PrivilegeMenu o where o.key.roleCode=:roleCode")
    List<PrivilegeMenu> getPrivilegeRole(@Param("roleCode") String roleCode);

    @Query("select o from PrivilegeMenu o where o.key.compCode=:compCode")
    List<PrivilegeMenu> getPrivilegeCompany(@Param("compCode") String compCode);

}
