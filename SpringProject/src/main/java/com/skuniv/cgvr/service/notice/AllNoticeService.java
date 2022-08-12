package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.dto.notice.AllNoticeDto;
import com.skuniv.cgvr.repository.notice.AllNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllNoticeService {
    private AllNoticeRepository allNoticeRepository;

    @Transactional
    public List<AllNoticeDto> findAllDesc() {
        return allNoticeRepository.findAllDesc().stream()
                .map(AllNoticeDto::new)
                .collect(Collectors.toList());
    }
}
