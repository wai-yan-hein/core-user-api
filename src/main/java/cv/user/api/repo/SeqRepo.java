package cv.user.api.repo;

import cv.user.api.entity.SeqKey;
import cv.user.api.entity.SeqTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeqRepo extends JpaRepository<SeqTable, SeqKey> {
}
