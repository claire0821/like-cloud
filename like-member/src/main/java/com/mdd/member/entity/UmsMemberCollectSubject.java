package com.mdd.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 会员收藏的专题活动实体
 */
@Data
public class UmsMemberCollectSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long subjectId; // subject_id
    private String subjectName; // subject_name
    private String subjectImg; // subject_img
    private String subjectUrll; // 活动url

}