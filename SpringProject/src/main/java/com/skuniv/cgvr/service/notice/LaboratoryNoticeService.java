package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.LaboratoryNotice;
import com.skuniv.cgvr.repository.notice.LaboratoryNoticeRepository;
import com.skuniv.cgvr.dto.notice.LaboratoryNoticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaboratoryNoticeService {
    private final LaboratoryNoticeRepository laboratory_notice_repository;

    @Autowired
    public LaboratoryNoticeService(LaboratoryNoticeRepository laboratory_notice_repository) {
        this.laboratory_notice_repository = laboratory_notice_repository;
    }
    //@Transactional(readOnly = true) // 조회 기능만 남김
    /* 단순 조회 */
    @Transactional
    public List<LaboratoryNoticeDto> findAll() {
        List<LaboratoryNotice> boards = laboratory_notice_repository.findAll();
        List<LaboratoryNoticeDto> DtoList = new ArrayList<>();

        for(LaboratoryNotice board : boards) {
            LaboratoryNoticeDto Dto = LaboratoryNoticeDto.builder()
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
    public List<LaboratoryNoticeDto> findAllDesc() {
        return laboratory_notice_repository.findAllDesc().stream()
                .map(LaboratoryNoticeDto::new)
                .collect(Collectors.toList());
    }
    public LaboratoryNoticeDto findById(Integer id) {
        LaboratoryNotice entity = laboratory_notice_repository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않음. id = " + id));
        return new LaboratoryNoticeDto(entity);
    }
    @Transactional
    public Integer savePost(LaboratoryNoticeDto Dto) {
        return laboratory_notice_repository.save(Dto.toEntity()).getId();
    }
}