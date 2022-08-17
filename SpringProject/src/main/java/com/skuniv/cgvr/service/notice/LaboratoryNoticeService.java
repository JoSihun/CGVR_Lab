package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.LaboratoryNotice;
import com.skuniv.cgvr.dto.notice.LaboratoryNoticeListResponseDto;
import com.skuniv.cgvr.dto.notice.LaboratoryNoticeResponseDto;
import com.skuniv.cgvr.dto.notice.LaboratoryNoticeSaveRequestDto;
import com.skuniv.cgvr.dto.notice.LaboratoryNoticeUpdateRequestDto;
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
    public Long save(LaboratoryNoticeSaveRequestDto requestDto) {
        return laboratoryNoticeRepository.save(requestDto.toEntity()).getId();
    }

    // update
    @Transactional
    public Long update(Long id, LaboratoryNoticeUpdateRequestDto requestDto) {
        LaboratoryNotice laboratoryNotice = laboratoryNoticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        laboratoryNotice.update(requestDto.getTitle(), requestDto.getContent(),
                requestDto.getCategory1_id(), requestDto.getCategory2_id(), requestDto.getCategory3_id(),
                requestDto.getAttachment_id());

        return id;
    }

    // findById
    public LaboratoryNoticeResponseDto findById(Long id) {
        LaboratoryNotice entity = laboratoryNoticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id)
        );
        return new LaboratoryNoticeResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<LaboratoryNoticeListResponseDto> findAllDesc() {
        return laboratoryNoticeRepository.findAllDesc().stream()
                .map(LaboratoryNoticeListResponseDto::new)
                .collect(Collectors.toList());
    }

    // delete
    @Transactional
    public void delete(Long id) {
        LaboratoryNotice laboratoryNotice = laboratoryNoticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        laboratoryNoticeRepository.delete(laboratoryNotice);
    }
}
