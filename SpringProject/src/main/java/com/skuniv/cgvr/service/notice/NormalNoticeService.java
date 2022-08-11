package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import com.skuniv.cgvr.repository.notice.NormalNoticeRepository;
import com.skuniv.cgvr.dto.notice.NormalNoticeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NormalNoticeService {
    private final NormalNoticeRepository normalNoticeRepository;

    @Transactional
    public Long save(NormalNoticeSaveRequestDto requestDto) {
        return normalNoticeRepository.save(requestDto.toEntity()).getId();
    }

}