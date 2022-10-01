package cv.user.api.repo;

import cv.user.api.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInfoRepo extends JpaRepository<CompanyInfo,String> {
}
