package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.LaboratoryNotice;
import com.skuniv.cgvr.dto.notice.LaboratoryNoticeListResponseDto;
import com.skuniv.cgvr.dto.notice.LaboratoryResponseDto;
import com.skuniv.cgvr.dto.notice.LaboratorySaveRequestDto;
import com.skuniv.cgvr.repository.notice.LaboratoryNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LaboratoryNoticeService {
    private final LaboratoryNoticeRepository laboratoryNoticeRepository;

    // insert
    @Transactional
    public Long save(LaboratorySaveRequestDto requestDto) {
        return this.laboratoryNoticeRepository.save(requestDto.toEntity()).getId();
    }

    // update

    // findById
    public LaboratoryResponseDto findById(Long id) {
        LaboratoryNotice entity = laboratoryNoticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id)
        );
        return new LaboratoryResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<LaboratoryNoticeListResponseDto> findAllDesc() {
        return laboratoryNoticeRepository.findAllDesc().stream()
                .map(LaboratoryNoticeListResponseDto::new)
                .collect(Collectors.toList());
    }

    // delete
}
