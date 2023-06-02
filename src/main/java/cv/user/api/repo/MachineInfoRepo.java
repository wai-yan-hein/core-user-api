package cv.user.api.repo;

import cv.user.api.entity.MachineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface MachineInfoRepo extends JpaRepository<MachineInfo, Integer> {
    @Query("select o from MachineInfo o where o.machineName = :name")
    List<MachineInfo> findByName(@Param("name") String macName);
    @Query("select o from MachineInfo o where o.serialNo = :serialNo")
    List<MachineInfo> findBySerialNo(@Param("serialNo") String serialNo);
    @Query(value = "select ifnull(max(mac_id),1)+1 mac_id from machine_info", nativeQuery = true)
    Integer findMaxId();
    @Query("select o from MachineInfo o where o.updatedDate > :updatedDate")
    List<MachineInfo> getMachineInfoByDate(@Param("updatedDate") Timestamp updatedDate);
}
