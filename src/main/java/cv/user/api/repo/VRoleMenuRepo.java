package cv.user.api.repo;

import cv.user.api.entity.VRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VRoleMenuRepo extends JpaRepository<VRoleMenu, String> {

    @Query("select o from VRoleMenu o where o.roleCode = :role_code and o.parentMenuCode = :parent and o.menuType = 'Menu' order by o.orderBy")
    List<VRoleMenu> getMenuChild(@Param("role_code") String roleCode, @Param("parent") String parentCode);

    @Query("select o from VRoleMenu o where o.roleCode = :role_code and o.parentMenuCode = :parent order by o.orderBy")
    List<VRoleMenu> getMenu(@Param("role_code") String roleCode, @Param("parent") String parentCode);

    @Query("select o from VRoleMenu o where o.roleCode = :role_code  and o.menuType = 'Report' order by o.menuName")
    List<VRoleMenu> getReport(@Param("role_code") String roleCode);
}
