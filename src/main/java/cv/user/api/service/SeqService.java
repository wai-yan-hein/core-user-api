package cv.user.api.service;

import cv.user.api.entity.SeqKey;
import cv.user.api.entity.SeqTable;
import cv.user.api.repo.SeqRepo;

public interface SeqService {
    SeqTable save(SeqTable s);

    SeqTable findByKey(SeqKey key);
    int getSeqNo(SeqKey key);
}
