package cv.user.api.repo;

import cv.user.api.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface CompanyInfoRepo extends JpaRepository<CompanyInfo, String> {
    @Query(value = "select * from company_info where active = 1", nativeQuery = true)
    List<CompanyInfo> getActiveCompany();

    @Query("select o from CompanyInfo o where o.updatedDate > :updatedDate")
    List<CompanyInfo> getCompanyInfoByDate(@Param("updatedDate") LocalDateTime updatedDate);
}
