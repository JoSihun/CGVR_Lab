package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import com.skuniv.cgvr.repository.notice.NormalNoticeRepository;
import com.skuniv.cgvr.dto.notice.NormalNoticeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NormalNoticeService {
    private final NormalNoticeRepository normal_notice_repository;

    public NormalNoticeService(NormalNoticeRepository normal_notice_repository) {
        this.normal_notice_repository = normal_notice_repository;
    }

    //@Transactional(readOnly = true) // 조회 기능만 남김
    /* 단순 조회 */
    @Transactional
    public List<NormalNoticeDto> findAll() {
        List<NormalNotice> boards = normal_notice_repository.findAll();
        List<NormalNoticeDto> DtoList = new ArrayList<>();

        for (NormalNotice board : boards) {
            NormalNoticeDto Dto = NormalNoticeDto.builder()
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
    @Transactional
    public List<NormalNoticeDto> findAllDesc() {
        return normal_notice_repository.findAllDesc().stream()
                .map(NormalNoticeDto::new)
                .collect(Collectors.toList());
    }
    public NormalNoticeDto findById(Integer id) {
        NormalNotice entity = normal_notice_repository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않음. id = " + id));
        return new NormalNoticeDto(entity);
    }
    @Transactional
    public Integer savePost(NormalNoticeDto Dto) {
        return normal_notice_repository.save(Dto.toEntity()).getId();
    }
}