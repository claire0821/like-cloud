package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.Attr;
import com.mdd.product.validate.AttrParam;
import com.mdd.product.vo.AttrListVo;
import com.mdd.product.vo.AttrDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品属性服务接口类
 */
public interface IAttrService extends IService<Attr> {

    /**
     * 商品属性列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<AttrVo>
     */
    PageResult<AttrListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品属性详情
     *
     * @param id 主键ID
     * @return Attr
     */
    AttrDetailVo detail(Long id);

    /**
     * 商品属性新增
     *
     * @param attrParam 参数
     */
    void add(AttrParam attrParam);

    /**
     * 商品属性编辑
     *
     * @param attrParam 参数
     */
    void edit(AttrParam attrParam);

    /**
     * 商品属性删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    void update(AttrDetailVo attrDetailVo);
    void save(AttrListVo attrListVo);
}
