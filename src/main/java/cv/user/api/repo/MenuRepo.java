package cv.user.api.repo;

import cv.user.api.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepo extends JpaRepository<Menu, String> {
    @Query("select o from Menu o where o.parentMenuCode = :parent and o.key.compCode = :compCode order by o.orderBy")
    List<Menu> getMenuChild(@Param("parent") String parentCode,@Param("compCode") String compCode);

    @Query("select o from Menu o where (o.menuClass='AllCash' or o.menuClass='DayBook') and (o.account is null or o.account ='')")
    List<Menu> getMenuDynamic();

}
