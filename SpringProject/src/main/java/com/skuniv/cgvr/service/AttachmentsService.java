package com.skuniv.cgvr.service;

import com.skuniv.cgvr.domain.Attachments;
import com.skuniv.cgvr.domain.posts.Posts;
import com.skuniv.cgvr.dto.AttachmentsListResponseDto;
import com.skuniv.cgvr.repository.AttachmentsRepository;
import com.skuniv.cgvr.repository.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AttachmentsService {
    private final PostsRepository postsRepository;
    private final AttachmentsRepository attachmentsRepository;

    @Transactional
    public List<AttachmentsListResponseDto> findAllByPostId(Long postId) {
        Posts entity = this.postsRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));
        List<Attachments> attachmentsList = entity.getAttachmenstList();
        return attachmentsList.stream().map(AttachmentsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public AttachmentsResponseDto findById(Long attachmentId) {
        Attachments entity = this.attachmentsRepository.findById(attachmentId).orElseThrow(
                () -> new IllegalArgumentException("해당 파일이 존재하지 않습니다. id=" + attachmentId));
        return new AttachmentsListResponseDto(entity);
        
    }
}
