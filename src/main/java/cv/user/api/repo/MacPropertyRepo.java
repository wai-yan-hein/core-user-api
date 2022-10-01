package cv.user.api.repo;

import cv.user.api.entity.MachineProperty;
import cv.user.api.entity.MachinePropertyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MacPropertyRepo extends JpaRepository<MachineProperty, MachinePropertyKey> {
    @Query("select o from MachineProperty o where o.key.macId =:macId ")
    List<MachineProperty> getMacProperty(@Param("macId") Integer macId);
}
