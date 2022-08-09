package com.skuniv.cgvr.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  // 데이터 조작 시 자동 날짜 수정을 위한 리스너
public class TimeEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime reg_date;

    @LastModifiedDate
    @Column
    private LocalDateTime mod_date;

}
