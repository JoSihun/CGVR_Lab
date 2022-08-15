package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import com.skuniv.cgvr.dto.notice.NormalNoticeResponseDto;
import com.skuniv.cgvr.dto.notice.NormalNoticeUpdateRequestDto;
import com.skuniv.cgvr.repository.notice.NormalNoticeRepository;
import com.skuniv.cgvr.dto.notice.NormalNoticeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NormalNoticeService {
    private final NormalNoticeRepository normalNoticeRepository;

    /* 게시판 글목록 */
    @Transactional
    public List<NormalNoticeResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "regDate");
        List<NormalNotice> normalNoticeList = this.normalNoticeRepository.findAll(sort);
        return normalNoticeList.stream().map(NormalNoticeResponseDto::new).collect(Collectors.toList());
    }


    /* 게시글 상세보기 */
    @Transactional
    public NormalNoticeResponseDto findById(final Long id) {
        NormalNotice entity = normalNoticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다. id = " + id));
        entity.increaseHits();
        return new NormalNoticeResponseDto(entity);
    }


    /* 게시글 저장하기 */
    @Transactional
    public Long save(final NormalNoticeSaveRequestDto requestDto) {
        return this.normalNoticeRepository.save(requestDto.toEntity()).getId();
    }


    /* 게시글 수정하기 */
    @Transactional
    public Long update(final Long id, final NormalNoticeUpdateRequestDto requestDto){
        NormalNotice entity = normalNoticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id)
        );
        entity.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getAuthor(),
                requestDto.getCategory1_id(), requestDto.getCategory2_id(), requestDto.getCategory3_id(),
                requestDto.getAttachment_id());
        return id;
    }


    /* 게시글 삭제하기 */
    @Transactional
    public Long delete(final Long id) {
        NormalNotice entity = normalNoticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("이미 존재하지 않는 게시글입니다. id=" + id));
        entity.delete();
        return id;
    }
}