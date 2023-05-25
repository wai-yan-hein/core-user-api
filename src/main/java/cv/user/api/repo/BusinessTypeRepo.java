package cv.user.api.repo;

import cv.user.api.entity.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface BusinessTypeRepo extends JpaRepository<BusinessType, Integer> {
    @Query(value = "select ifnull(max(bus_id),1)+1 bus_id from business_type", nativeQuery = true)
    Integer findMaxId();

    @Query("select o from BusinessType o where o.updatedDate > :updatedDate")
    List<BusinessType> getBusinessTypeByDate(@Param("updatedDate") Timestamp updatedDate);
}
