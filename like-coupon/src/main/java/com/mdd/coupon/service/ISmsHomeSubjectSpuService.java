package com.mdd.coupon.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsHomeSubjectSpuParam;
import com.mdd.coupon.vo.SmsHomeSubjectSpuListVo;
import com.mdd.coupon.vo.SmsHomeSubjectSpuDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 专题商品服务接口类
 */
public interface ISmsHomeSubjectSpuService {

    /**
     * 专题商品列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsHomeSubjectSpuVo>
     */
    PageResult<SmsHomeSubjectSpuListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 专题商品详情
     *
     * @param id 主键ID
     * @return SmsHomeSubjectSpu
     */
    SmsHomeSubjectSpuDetailVo detail(Long id);

    /**
     * 专题商品新增
     *
     * @param smsHomeSubjectSpuParam 参数
     */
    void add(SmsHomeSubjectSpuParam smsHomeSubjectSpuParam);

    /**
     * 专题商品编辑
     *
     * @param smsHomeSubjectSpuParam 参数
     */
    void edit(SmsHomeSubjectSpuParam smsHomeSubjectSpuParam);

    /**
     * 专题商品删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
