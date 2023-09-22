package cv.user.api.service;

import cv.user.api.entity.SeqKey;
import cv.user.api.entity.SeqTable;

public interface SeqService {
    SeqTable save(SeqTable s);

    int getSeqNo(SeqKey key);
}
