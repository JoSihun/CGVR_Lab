package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.LectureNotice;
import com.skuniv.cgvr.dto.notice.LectureNoticeDto;
import com.skuniv.cgvr.repository.notice.LectureNoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureNoticeService {
    private final LectureNoticeRepository lecture_notice_repository;

    public LectureNoticeService(LectureNoticeRepository lecture_notice_repository) {
        this.lecture_notice_repository = lecture_notice_repository;
    }
    //@Transactional(readOnly = true) // 조회 기능만 남김
    /* 단순 조회 */
    @Transactional
    public List<LectureNoticeDto> findAll() {
        List<LectureNotice> boards = lecture_notice_repository.findAll();
        List<LectureNoticeDto> DtoList = new ArrayList<>();

        for(LectureNotice board : boards) {
            LectureNoticeDto Dto = LectureNoticeDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .hits(board.getHits())
                    .regDate(board.getRegDate())
                    .modDate(board.getModDate())
                    .author(board.getAuthor())
                    .tag1_id(board.getTag1_id())
                    .tag2_id(board.getTag2_id())
                    .tag3_id(board.getTag3_id())
                    .attachment_id(board.getAttachment_id())
                    .build();
            DtoList.add(Dto);
        }
        return DtoList;
    }
    /* 역순 조회 */
    @Transactional
    public List<LectureNoticeDto> findAllDesc() {
        return lecture_notice_repository.findAllDesc().stream()
                .map(LectureNoticeDto::new)
                .collect(Collectors.toList());
    }
    public LectureNoticeDto findById(Integer id) {
        LectureNotice entity = lecture_notice_repository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않음. id = " + id));
        return new LectureNoticeDto(entity);
    }
    @Transactional
    public Integer savePost(LectureNoticeDto Dto) {
        return lecture_notice_repository.save(Dto.toEntity()).getId();
    }
}
