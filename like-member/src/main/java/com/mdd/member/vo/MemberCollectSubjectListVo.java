package com.mdd.member.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * MemberCollectSubjectVo
 */
@Data
public class MemberCollectSubjectListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long subjectId;  // subject_id
    private String subjectName;  // subject_name
    private String subjectImg;  // subject_img
    private String subjectUrll;  // 活动url

}
