package cv.user.api.repo;

import cv.user.api.entity.Project;
import cv.user.api.entity.ProjectKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, ProjectKey> {
    
    @Query("select o from Project o where o.updatedDate > :updatedDate")
    List<Project> getProjectByDate(@Param("updatedDate") LocalDateTime updatedDate);

}
