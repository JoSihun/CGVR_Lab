package com.skuniv.cgvr.repository;

import com.skuniv.cgvr.domain.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Projects, Long> {
}
