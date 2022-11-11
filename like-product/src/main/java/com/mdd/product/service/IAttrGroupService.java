package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.AttrGroup;
import com.mdd.product.validate.AttrGroupParam;
import com.mdd.product.vo.AttrGroupListVo;
import com.mdd.product.vo.PmsAttrGroupDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 属性分组服务接口类
 */
public interface IAttrGroupService extends IService<AttrGroup> {

    /**
     * 属性分组列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsAttrGroupVo>
     */
    PageResult<AttrGroupListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 属性分组列表
     * @param pageParam 分页参数
     * @param key 搜索参数
     * @param catelogId 所属分类id
     * @return PageResult<PmsAttrGroupVo>
     */
    PageResult<AttrGroupListVo> list(PageParam pageParam, String key, Long catelogId);

    /**
     * 属性分组详情
     *
     * @param id 主键ID
     * @return PmsAttrGroup
     */
    PmsAttrGroupDetailVo detail(Long id);

    /**
     * 属性分组新增
     *
     * @param attrGroupParam 参数
     */
    void add(AttrGroupParam attrGroupParam);

    /**
     * 属性分组编辑
     *
     * @param attrGroupParam 参数
     */
    void edit(AttrGroupParam attrGroupParam);

    /**
     * 属性分组删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
