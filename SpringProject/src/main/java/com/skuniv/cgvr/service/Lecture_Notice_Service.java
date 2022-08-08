package com.skuniv.cgvr.service;

import com.skuniv.cgvr.domain.entity.Lecture_Notice_Board;
import com.skuniv.cgvr.dto.Lecture_Notice_Dto;
import com.skuniv.cgvr.domain.repository.Lecture_Notice_Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class Lecture_Notice_Service {
    private final Lecture_Notice_Repository lecture_notice_repository;

    public Lecture_Notice_Service(Lecture_Notice_Repository lecture_notice_repository) {
        this.lecture_notice_repository = lecture_notice_repository;
    }
    //@Transactional(readOnly = true) // 조회 기능만 남김
    @Transactional
    public List<Lecture_Notice_Dto> getList() {
        List<Lecture_Notice_Board> boards = lecture_notice_repository.findAll();
        List<Lecture_Notice_Dto> DtoList = new ArrayList<>();

        for(Lecture_Notice_Board board : boards) {
            Lecture_Notice_Dto Dto = Lecture_Notice_Dto.builder()
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
    public Integer savePost(Lecture_Notice_Dto Dto) {
        return lecture_notice_repository.save(Dto.toEntity()).getId();
    }
}
