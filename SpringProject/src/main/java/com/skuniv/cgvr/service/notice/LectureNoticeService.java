package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.LectureNotice;
import com.skuniv.cgvr.dto.notice.LectureNoticeDto;
import com.skuniv.cgvr.repository.notice.LectureNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureNoticeService {
    private final LectureNoticeRepository lecture_notice_repository;

    @Autowired
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
                    .category1_id(board.getCategory1_id())
                    .category2_id(board.getCategory2_id())
                    .category3_id(board.getCategory3_id())
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
    /* 역순 조회 및 날짜포매팅 */
    @Transactional
    public List<HashMap> findAllDescAsMap() {
        // 게시글 리스트를 위한 DtoList 담기
        List<LectureNoticeDto> noticeDtoList = lecture_notice_repository.findAllDesc().stream()
                .map(LectureNoticeDto::new)
                .collect(Collectors.toList());
        // 반환할 HashMapList 생성
        List<HashMap> noticeHashList = new ArrayList<>();
        HashMap<String, String> map;
        for(LectureNoticeDto lectureNoticeDto : noticeDtoList) {
            if (lectureNoticeDto.getRegDate().getDayOfYear() == LocalDateTime.now().getDayOfYear()
                    && lectureNoticeDto.getRegDate().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()
                    && lectureNoticeDto.getRegDate().getDayOfWeek() == LocalDateTime.now().getDayOfWeek()) {
                // 오늘 올라온 게시글이면 시간만 나오도록 출력
                map = new HashMap<>();
                map.put("id", Long.toString(lectureNoticeDto.getId()));
                map.put("title", lectureNoticeDto.getTitle());
                map.put("author", lectureNoticeDto.getAuthor());
                map.put("hits", Long.toString(lectureNoticeDto.getHits()));
                map.put("regDate_formatted", lectureNoticeDto.getRegDate().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm")));
                noticeHashList.add(map);
            } else {
                // 그 외 게시글은 날짜만 나오도록 출력
                map = new HashMap<>();
                map.put("id", Long.toString(lectureNoticeDto.getId()));
                map.put("title", lectureNoticeDto.getTitle());
                map.put("author", lectureNoticeDto.getAuthor());
                map.put("hits", Long.toString(lectureNoticeDto.getHits()));
                map.put("regDate_formatted", lectureNoticeDto.getRegDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd.")));
                noticeHashList.add(map);
            }
        }
        return noticeHashList;
    }
    @Transactional
    public LectureNoticeDto findById(Long id) {
        LectureNotice entity = lecture_notice_repository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않음. id = " + id));
        return new LectureNoticeDto(entity);
    }
    @Transactional
    public Long savePost(LectureNoticeDto Dto) {
        return lecture_notice_repository.save(Dto.toEntity()).getId();
    }
}
