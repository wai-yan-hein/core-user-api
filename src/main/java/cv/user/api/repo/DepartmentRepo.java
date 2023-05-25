package cv.user.api.repo;

import cv.user.api.entity.CompanyInfo;
import cv.user.api.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    @Query("select o from Department o where date(o.updatedDate) > :updatedDate")
    List<Department> getDepartmentByDate(@Param("updatedDate") Date updatedDate);
}
