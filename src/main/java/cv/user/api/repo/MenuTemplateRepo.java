package cv.user.api.repo;

import cv.user.api.entity.MenuTemplate;
import cv.user.api.entity.MenuTemplateKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuTemplateRepo extends JpaRepository<MenuTemplate, MenuTemplateKey> {
    @Query(value = "select ifnull(max(menu_id),0)+1 menu_id from menu_template", nativeQuery = true)
    String findMaxId();

    @Query("select o from MenuTemplate o where o.parentMenuId = :parent and o.key.busId = :busId order by o.orderBy")
    List<MenuTemplate> getMenuChild(@Param("parent") String parentCode, @Param("busId") Integer busId);

    @Query("select o from MenuTemplate o where o.key.busId = :busId")
    List<MenuTemplate> findAll(@Param("busId") Integer busId);
}
