package cv.user.api.repo;

import cv.user.api.entity.RoleProperty;
import cv.user.api.entity.SeqKey;
import cv.user.api.entity.SeqTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SeqRepo extends JpaRepository<SeqTable, SeqKey> {
    @Query("select o from SeqTable o where date(o.updatedDate) > :updatedDate")
    List<SeqTable> getSeqTableByDate(@Param("updatedDate") Date updatedDate);
}
