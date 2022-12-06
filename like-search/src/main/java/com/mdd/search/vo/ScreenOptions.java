package com.mdd.search.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-25 17:45
 **/
@Data
public class ScreenOptions {
    private Long id;
    private String name;
    private String url;
    private String img;
    private boolean selected = false;//是否选择
    private List<Value> values;

    @Data
    public static class Value {
        private boolean selected = false;//是否选择
        private String value;
    }
}
