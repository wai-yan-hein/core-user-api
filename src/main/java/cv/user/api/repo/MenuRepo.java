package cv.user.api.repo;

import cv.user.api.entity.Menu;
import cv.user.api.entity.VRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepo extends JpaRepository<Menu, String> {
    @Query("select o from Menu o where o.parentMenuCode = :parent order by o.orderBy")
    List<Menu> getMenuChild(@Param("parent") String parentCode);

}
