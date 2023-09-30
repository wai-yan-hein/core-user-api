package cv.user.api.repo;

import cv.user.api.entity.DateLock;
import cv.user.api.entity.DateLockKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DateLockRepo extends JpaRepository<DateLock, DateLockKey> {
    @Query("select o from DateLock o where o.updatedDate > :updatedDate")
    List<DateLock> getDateLockByDate(@Param("updatedDate") LocalDateTime updatedDate);
}
