package cv.user.api.repo;

import cv.user.api.entity.MachineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MachineInfoRepo extends JpaRepository<MachineInfo, Integer> {
    @Query("select o from MachineInfo o where o.machineName = :name")
    List<MachineInfo> findByName(@Param("name") String macName);
}
