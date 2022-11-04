package com.mdd.coupon.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsHomeSubjectParam;
import com.mdd.coupon.vo.SmsHomeSubjectListVo;
import com.mdd.coupon.vo.SmsHomeSubjectDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】服务接口类
 */
public interface ISmsHomeSubjectService {

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsHomeSubjectVo>
     */
    PageResult<SmsHomeSubjectListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】详情
     *
     * @param id 主键ID
     * @return SmsHomeSubject
     */
    SmsHomeSubjectDetailVo detail(Long id);

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】新增
     *
     * @param smsHomeSubjectParam 参数
     */
    void add(SmsHomeSubjectParam smsHomeSubjectParam);

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】编辑
     *
     * @param smsHomeSubjectParam 参数
     */
    void edit(SmsHomeSubjectParam smsHomeSubjectParam);

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
