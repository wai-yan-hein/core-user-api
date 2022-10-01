package cv.user.api.repo;

import cv.user.api.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepo extends JpaRepository<AppRole, String> {
}
