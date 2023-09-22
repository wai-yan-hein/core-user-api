package cv.user.api.service;

import cv.user.api.entity.SeqKey;
import cv.user.api.entity.SeqTable;
import cv.user.api.repo.SeqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SeqServiceImpl implements SeqService {
    @Autowired
    private SeqRepo seqRepo;

    @Override
    public SeqTable save(SeqTable s) {
        return seqRepo.save(s);
    }


    @Override
    public int getSeqNo(SeqKey key) {
        SeqTable seqTable = new SeqTable();
        Optional<SeqTable> st = seqRepo.findById(key);
        if (st.isPresent()) {
            seqTable.setSeqNo(st.get().getSeqNo() + 1);
        } else {
            seqTable.setSeqNo(1);
        }
        seqTable.setKey(key);
        seqRepo.save(seqTable);
        return seqTable.getSeqNo();
    }

}
