package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.HomeSubjectSpu;
import com.mdd.coupon.validate.HomeSubjectSpuParam;
import com.mdd.coupon.vo.HomeSubjectSpuListVo;
import com.mdd.coupon.vo.HomeSubjectSpuDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 专题商品服务接口类
 */
public interface IHomeSubjectSpuService extends IService<HomeSubjectSpu> {

    /**
     * 专题商品列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<HomeSubjectSpuVo>
     */
    PageResult<HomeSubjectSpuListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 专题商品详情
     *
     * @param id 主键ID
     * @return HomeSubjectSpu
     */
    HomeSubjectSpuDetailVo detail(Long id);

    /**
     * 专题商品新增
     *
     * @param homeSubjectSpuParam 参数
     */
    void add(HomeSubjectSpuParam homeSubjectSpuParam);

    /**
     * 专题商品编辑
     *
     * @param homeSubjectSpuParam 参数
     */
    void edit(HomeSubjectSpuParam homeSubjectSpuParam);

    /**
     * 专题商品删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
