package com.skuniv.cgvr.repository;

import com.skuniv.cgvr.domain.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {
}
