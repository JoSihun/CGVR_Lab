package com.skuniv.cgvr.service.notice;

import com.skuniv.cgvr.domain.notice.LectureNoticeAnswer;
import com.skuniv.cgvr.dto.notice.LectureNoticeAnswerResponseDto;
import com.skuniv.cgvr.dto.notice.LectureNoticeAnswerSaveRequestDto;
import com.skuniv.cgvr.repository.notice.LectureNoticeAnswerRepository;
import com.skuniv.cgvr.repository.notice.LectureNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LectureNoticeAnswerService {
    private final LectureNoticeAnswerRepository lectureNoticeAnswerRepository;
    private final LectureNoticeRepository lectureNoticeRepository;

    @Transactional
    public List<LectureNoticeAnswerResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "regDate");
        List<LectureNoticeAnswer> lectureNoticeList = this.lectureNoticeAnswerRepository.findAll(sort);
        return lectureNoticeList.stream().map(LectureNoticeAnswerResponseDto::new).collect(Collectors.toList());
    }
    /* 댓글 저장하기 */
    @Transactional
    public Long save(Long id, LectureNoticeAnswerSaveRequestDto requestDto) {
        requestDto.setLectureNotice(lectureNoticeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다." + id)));
        return lectureNoticeAnswerRepository.save(requestDto.toEntity()).getId();
    }
}
