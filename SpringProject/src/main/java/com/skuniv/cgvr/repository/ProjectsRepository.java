package com.skuniv.cgvr.repository;

import com.skuniv.cgvr.domain.Projects;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    Projects findByProjectName(String projectName);
}
