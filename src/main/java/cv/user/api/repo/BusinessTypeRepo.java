package cv.user.api.repo;

import cv.user.api.entity.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusinessTypeRepo extends JpaRepository<BusinessType, Integer> {
    @Query(value = "select ifnull(max(bus_id),1)+1 bus_id from business_type", nativeQuery = true)
    Integer findMaxId();
}
