package com.mdd.coupon.service;

import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsSeckillSessionParam;
import com.mdd.coupon.vo.SmsSeckillSessionListVo;
import com.mdd.coupon.vo.SmsSeckillSessionDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 秒杀活动场次服务接口类
 */
public interface ISmsSeckillSessionService {

    /**
     * 秒杀活动场次列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSeckillSessionVo>
     */
    PageResult<SmsSeckillSessionListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 秒杀活动场次详情
     *
     * @param id 主键ID
     * @return SmsSeckillSession
     */
    SmsSeckillSessionDetailVo detail(Long id);

    /**
     * 秒杀活动场次新增
     *
     * @param smsSeckillSessionParam 参数
     */
    void add(SmsSeckillSessionParam smsSeckillSessionParam);

    /**
     * 秒杀活动场次编辑
     *
     * @param smsSeckillSessionParam 参数
     */
    void edit(SmsSeckillSessionParam smsSeckillSessionParam);

    /**
     * 秒杀活动场次删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
