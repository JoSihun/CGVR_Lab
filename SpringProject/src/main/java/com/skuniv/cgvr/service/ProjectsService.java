package com.skuniv.cgvr.service;

import com.skuniv.cgvr.domain.Projects;
import com.skuniv.cgvr.dto.ProjectsListResponseDto;
import com.skuniv.cgvr.dto.ProjectsSaveRequestDto;
import com.skuniv.cgvr.dto.ProjectsUpdateRequestDto;
import com.skuniv.cgvr.repository.ProjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectsService {
    private final ProjectsRepository projectsRepository;

    /** 프로젝트명 목록보기 */
    @Transactional
    public List<ProjectsListResponseDto> findAllAsc() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Projects> projectsList = this.projectsRepository.findAll(sort);
        return projectsList.stream().map(ProjectsListResponseDto::new).collect(Collectors.toList());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /** 프로젝트명 저장하기 */
    @Transactional
    public Long save(final ProjectsSaveRequestDto requestDto) {
        return this.projectsRepository.save(requestDto.toEntity()).getId();
    }


    /** 프로젝트명 수정하기 */
    @Transactional
    public Long update(final Long id, final ProjectsUpdateRequestDto requestDto) {
        Projects entity = this.projectsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 프로젝트명이 존재하지 않습니다. id=" + id));
        entity.update(requestDto.getProjectName());
        return id;
    }


    /** 프로젝트명 삭제하기 */
    @Transactional
    public Long delete(final Long id) {
        Projects entity = this.projectsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("이미 존재하지 않는 프로젝트명입니다. id=" + id));
        this.projectsRepository.delete(entity);
        return id;
    }
}
