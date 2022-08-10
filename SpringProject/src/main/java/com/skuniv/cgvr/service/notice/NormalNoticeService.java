package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import com.skuniv.cgvr.repository.notice.NormalNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NormalNoticeService {
    private final NormalNoticeRepository normalRepository;

    public List<NormalNotice> getList() {
        return this.normalRepository.findAll();
    }
}
