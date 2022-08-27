package com.skuniv.cgvr.service;

import com.skuniv.cgvr.domain.Project;
import com.skuniv.cgvr.dto.project.ProjectListResponseDto;
import com.skuniv.cgvr.dto.project.ProjectSaveRequestDto;
import com.skuniv.cgvr.dto.project.ProjectUpdateRequestDto;
import com.skuniv.cgvr.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectsRepository;

    /** 프로젝트명 목록보기 */
    @Transactional
    public List<ProjectListResponseDto> findAllAsc() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Project> projectsList = this.projectsRepository.findAll(sort);
        return projectsList.stream().map(ProjectListResponseDto::new).collect(Collectors.toList());
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /** 프로젝트명 저장하기 */
    @Transactional
    public Long save(final ProjectSaveRequestDto requestDto) {
        return this.projectsRepository.save(requestDto.toEntity()).getId();
    }


    /** 프로젝트명 수정하기 */
    @Transactional
    public Long update(final Long id, final ProjectUpdateRequestDto requestDto) {
        Project entity = this.projectsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 프로젝트명이 존재하지 않습니다. id=" + id));
        entity.update(requestDto.getProjectName());
        return id;
    }


    /** 프로젝트명 삭제하기 */
    @Transactional
    public Long delete(final Long id) {
        Project entity = this.projectsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("이미 존재하지 않는 프로젝트명입니다. id=" + id));
        this.projectsRepository.delete(entity);
        return id;
    }
}
