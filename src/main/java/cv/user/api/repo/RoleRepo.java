package cv.user.api.repo;

import cv.user.api.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RoleRepo extends JpaRepository<AppRole, String> {
    
    @Query("select o from AppRole date(o.updated_date) > :updatedDate")
    List<AppRole> getProjectByDate(@Param("updatedDate") Date updatedDate);

}
