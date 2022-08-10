package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import com.skuniv.cgvr.repository.notice.NormalNoticeRepository;
import com.skuniv.cgvr.dto.notice.NormalNoticeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NormalNoticeService {
    private final NormalNoticeRepository normal_notice_repository;

    public NormalNoticeService(NormalNoticeRepository normal_notice_repository) {
        this.normal_notice_repository = normal_notice_repository;
    }
    //@Transactional(readOnly = true) // 조회 기능만 남김
    @Transactional
    public List<NormalNoticeDto> getList() {
        List<NormalNotice> boards = normal_notice_repository.findAll();
        List<NormalNoticeDto> DtoList = new ArrayList<>();

        for(NormalNotice board : boards) {
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
    public Integer savePost(NormalNoticeDto Dto) {
        return normal_notice_repository.save(Dto.toEntity()).getId();
    }
}