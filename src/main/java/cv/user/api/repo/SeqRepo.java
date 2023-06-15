package cv.user.api.repo;

import cv.user.api.entity.SeqKey;
import cv.user.api.entity.SeqTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface SeqRepo extends JpaRepository<SeqTable, SeqKey> {
    @Query("select o from SeqTable o where o.updatedDate > :updatedDate")
    List<SeqTable> getSeqTableByDate(@Param("updatedDate") LocalDateTime updatedDate);
}
