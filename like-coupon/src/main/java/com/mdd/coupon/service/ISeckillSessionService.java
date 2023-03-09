package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.common.vo.SeckillSessionWithSkusVo;
import com.mdd.coupon.entity.SeckillSession;
import com.mdd.coupon.validate.SeckillSessionParam;
import com.mdd.coupon.vo.SeckillSessionListVo;
import com.mdd.coupon.vo.SeckillSessionDetailVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 秒杀活动场次服务接口类
 */
public interface ISeckillSessionService extends IService<SeckillSession> {

    /**
     * 秒杀活动场次列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SeckillSessionVo>
     */
    PageResult<SeckillSessionListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 秒杀活动场次详情
     *
     * @param id 主键ID
     * @return SeckillSession
     */
    SeckillSessionDetailVo detail(Long id);

    /**
     * 秒杀活动场次新增
     *
     * @param seckillSessionParam 参数
     */
    void add(SeckillSessionParam seckillSessionParam);

    /**
     * 秒杀活动场次编辑
     *
     * @param seckillSessionParam 参数
     */
    void edit(SeckillSessionParam seckillSessionParam);

    /**
     * 秒杀活动场次删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    List<SeckillSessionWithSkusVo> getLates3DaySession();

}
