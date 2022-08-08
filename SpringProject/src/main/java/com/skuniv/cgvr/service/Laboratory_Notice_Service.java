package com.skuniv.cgvr.service;

import com.skuniv.cgvr.domain.entity.Laboratory_Notice_Board;
import com.skuniv.cgvr.domain.repository.Laboratory_Notice_Repository;
import com.skuniv.cgvr.dto.Laboratory_Notice_Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class Laboratory_Notice_Service {
    private final Laboratory_Notice_Repository laboratory_notice_repository;

    public Laboratory_Notice_Service(Laboratory_Notice_Repository laboratory_notice_repository) {
        this.laboratory_notice_repository = laboratory_notice_repository;
    }
    //@Transactional(readOnly = true) // 조회 기능만 남김
    @Transactional
    public List<Laboratory_Notice_Dto> getList() {
        List<Laboratory_Notice_Board> boards = laboratory_notice_repository.findAll();
        List<Laboratory_Notice_Dto> DtoList = new ArrayList<>();

        for(Laboratory_Notice_Board board : boards) {
            Laboratory_Notice_Dto Dto = Laboratory_Notice_Dto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .hits(board.getHits())
                    .reg_date(board.getReg_date())
                    .mod_date(board.getMod_date())
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
    public Integer savePost(Laboratory_Notice_Dto Dto) {
        return laboratory_notice_repository.save(Dto.toEntity()).getId();
    }
}
