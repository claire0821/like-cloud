package com.mdd.wave.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.entity.WareSku;
import com.mdd.wave.validate.WareSkuParam;
import com.mdd.wave.vo.WareSkuListVo;
import com.mdd.wave.vo.WareSkuDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品库存服务接口类
 */
public interface IWareSkuService extends IService<WareSku> {

    /**
     * 商品库存列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<WareSkuVo>
     */
    PageResult<WareSkuListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品库存详情
     *
     * @param id 主键ID
     * @return WareSku
     */
    WareSkuDetailVo detail(Long id);

    /**
     * 商品库存新增
     *
     * @param wareSkuParam 参数
     */
    void add(WareSkuParam wareSkuParam);

    /**
     * 商品库存编辑
     *
     * @param wareSkuParam 参数
     */
    void edit(WareSkuParam wareSkuParam);

    /**
     * 商品库存删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
