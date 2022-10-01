package cv.user.api.repo;

import cv.user.api.entity.VRoleCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VRoleCompanyRepo extends JpaRepository<VRoleCompany, String> {
    @Query("select o from VRoleCompany o where o.roleCode = :roleCode")
    List<VRoleCompany> getCompany(@Param("roleCode") String roleCode);

}
