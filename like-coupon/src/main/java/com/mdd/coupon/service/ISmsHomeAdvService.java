package com.mdd.coupon.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsHomeAdvParam;
import com.mdd.coupon.vo.SmsHomeAdvListVo;
import com.mdd.coupon.vo.SmsHomeAdvDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 首页轮播广告服务接口类
 */
public interface ISmsHomeAdvService {

    /**
     * 首页轮播广告列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsHomeAdvVo>
     */
    PageResult<SmsHomeAdvListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 首页轮播广告详情
     *
     * @param id 主键ID
     * @return SmsHomeAdv
     */
    SmsHomeAdvDetailVo detail(Long id);

    /**
     * 首页轮播广告新增
     *
     * @param smsHomeAdvParam 参数
     */
    void add(SmsHomeAdvParam smsHomeAdvParam);

    /**
     * 首页轮播广告编辑
     *
     * @param smsHomeAdvParam 参数
     */
    void edit(SmsHomeAdvParam smsHomeAdvParam);

    /**
     * 首页轮播广告删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
