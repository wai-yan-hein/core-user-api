package cv.user.api.repo;

import cv.user.api.entity.PCKey;
import cv.user.api.entity.PrivilegeCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PrivilegeCompanyRepo extends JpaRepository<PrivilegeCompany, PCKey> {
    @Query("select o from PrivilegeCompany o where o.key.roleCode = :roleCode")
    List<PrivilegeCompany> getRoleCompany(@Param("roleCode") String roleCode);

    @Query("select o from PrivilegeCompany o where date(o.updatedDate) > :updatedDate")
    List<PrivilegeCompany> getPCByDate(@Param("updatedDate") Date updatedDate);
}
