package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.AttrAttrgroupRelation;
import com.mdd.product.validate.AttrAttrgroupRelationParam;
import com.mdd.product.vo.AttrAttrgroupRelationListVo;
import com.mdd.product.vo.AttrAttrgroupRelationDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 属性&属性分组关联服务接口类
 */
public interface IAttrAttrgroupRelationService extends IService<AttrAttrgroupRelation> {

    /**
     * 属性&属性分组关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<AttrAttrgroupRelationVo>
     */
    PageResult<AttrAttrgroupRelationListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 属性&属性分组关联详情
     *
     * @param id 主键ID
     * @return AttrAttrgroupRelation
     */
    AttrAttrgroupRelationDetailVo detail(Long id);

    /**
     * 属性&属性分组关联新增
     *
     * @param attrAttrgroupRelationParam 参数
     */
    void add(AttrAttrgroupRelationParam attrAttrgroupRelationParam);

    /**
     * 属性&属性分组关联编辑
     *
     * @param attrAttrgroupRelationParam 参数
     */
    void edit(AttrAttrgroupRelationParam attrAttrgroupRelationParam);

    /**
     * 属性&属性分组关联删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
