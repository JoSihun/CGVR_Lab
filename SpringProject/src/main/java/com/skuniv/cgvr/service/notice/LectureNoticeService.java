package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.LectureNotice;
import com.skuniv.cgvr.dto.notice.LectureNoticeResponseDto;
import com.skuniv.cgvr.dto.notice.LectureNoticeSaveRequestDto;
import com.skuniv.cgvr.dto.notice.LectureNoticeUpdateRequestDto;
import com.skuniv.cgvr.repository.notice.LectureNoticeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureNoticeService {
    private final LectureNoticeRepository lectureNoticeRepository;

    public LectureNoticeService(LectureNoticeRepository lectureNoticeRepository) {
        this.lectureNoticeRepository = lectureNoticeRepository;
    }
    //@Transactional(readOnly = true) // 조회 기능만 남김
    /* list view */
    @Transactional
    public List<LectureNoticeResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "regDate");
        List<LectureNotice> lectureNoticeList = this.lectureNoticeRepository.findAll(sort);
        return lectureNoticeList.stream().map(LectureNoticeResponseDto::new).collect(Collectors.toList());
    }
    /* detail view */
    @Transactional
    public LectureNoticeResponseDto findById(Long id) {
        LectureNotice entity = lectureNoticeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않음. id = " + id));
        return new LectureNoticeResponseDto(entity);
    }
    /* 게시글 저장하기 */
    @Transactional
    public Long save(LectureNoticeSaveRequestDto requestDto) {
        return lectureNoticeRepository.save(requestDto.toEntity()).getId();
    }
    /* 게시글 수정하기 */
    @Transactional
    public Long update(final Long id, final LectureNoticeUpdateRequestDto requestDto){
        LectureNotice entity = lectureNoticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id)
        );
        entity.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getAuthor(),
                requestDto.getCategory1_id(), requestDto.getCategory2_id(), requestDto.getCategory3_id(),
                requestDto.getAttachment_id());
        return id;
    }
}
