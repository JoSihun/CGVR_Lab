package com.skuniv.cgvr.service;

import com.skuniv.cgvr.domain.entity.General_Notice_Board;
import com.skuniv.cgvr.domain.repository.General_Notice_Repository;
import com.skuniv.cgvr.dto.General_Notice_Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class General_Notice_Service {
    private final General_Notice_Repository general_notice_repository;

    public General_Notice_Service(General_Notice_Repository general_notice_repository) {
        this.general_notice_repository = general_notice_repository;
    }
    //@Transactional(readOnly = true) // 조회 기능만 남김
    @Transactional
    public List<General_Notice_Dto> getList() {
        List<General_Notice_Board> boards = general_notice_repository.findAll();
        List<General_Notice_Dto> DtoList = new ArrayList<>();

        for(General_Notice_Board board : boards) {
            General_Notice_Dto Dto = General_Notice_Dto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .hits(board.getHits())
                    .reg_date(board.getReg_date())
                    .mod_date(board.getMod_date())
                    .author(board.getAuthor())
                    .tag_id(board.getTag_id())
                    .attachment_id(board.getAttachment_id())
                    .build();
            DtoList.add(Dto);
        }

        return DtoList;
    }

    @Transactional
    public Integer savePost(General_Notice_Dto Dto) {
        return general_notice_repository.save(Dto.toEntity()).getId();
    }
}