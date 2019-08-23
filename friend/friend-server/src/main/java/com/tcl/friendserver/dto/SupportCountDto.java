package com.tcl.friendserver.dto;


import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 点赞数量 DTO。用于存储从 Redis 取出来的被点赞数量
 */

@Data
@Getter
public class SupportCountDto implements Serializable {

    private static final long serialVersionUID = -2856160546081194945L;

    private Long id;

    private Integer count;

    public SupportCountDto() {
    }

    public SupportCountDto(Long id, Integer count) {
        this.id = id;
        this.count = count;
    }
}
