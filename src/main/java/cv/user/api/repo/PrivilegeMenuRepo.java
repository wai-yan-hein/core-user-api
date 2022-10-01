package cv.user.api.repo;

import cv.user.api.entity.PMKey;
import cv.user.api.entity.PrivilegeMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeMenuRepo extends JpaRepository<PrivilegeMenu, PMKey> {
}
