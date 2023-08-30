package cv.user.api.repo;

import cv.user.api.entity.Department;
import cv.user.api.entity.DepartmentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, DepartmentKey> {
    @Query("select o from Department o where o.updatedDate > :updatedDate")
    List<Department> getDepartmentByDate(@Param("updatedDate") LocalDateTime updatedDate);

    @Query(value = "select ifnull(max(dept_id),1)+1 dept_id from department", nativeQuery = true)
    Integer findMaxId();

    @Query(value = "select * from department where deleted =false and active =:active", nativeQuery = true)
    List<Department> getDepartment(@Param("active") Boolean active);
    @Query(value = "select * from department where deleted =false", nativeQuery = true)
    List<Department> getDepartment();

}
