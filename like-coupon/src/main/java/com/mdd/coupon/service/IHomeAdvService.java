package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.HomeAdv;
import com.mdd.coupon.validate.HomeAdvParam;
import com.mdd.coupon.vo.HomeAdvListVo;
import com.mdd.coupon.vo.HomeAdvDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 首页轮播广告服务接口类
 */
public interface IHomeAdvService extends IService<HomeAdv> {

    /**
     * 首页轮播广告列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<HomeAdvVo>
     */
    PageResult<HomeAdvListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 首页轮播广告详情
     *
     * @param id 主键ID
     * @return HomeAdv
     */
    HomeAdvDetailVo detail(Long id);

    /**
     * 首页轮播广告新增
     *
     * @param homeAdvParam 参数
     */
    void add(HomeAdvParam homeAdvParam);

    /**
     * 首页轮播广告编辑
     *
     * @param homeAdvParam 参数
     */
    void edit(HomeAdvParam homeAdvParam);

    /**
     * 首页轮播广告删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
