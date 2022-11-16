package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.HomeSubject;
import com.mdd.coupon.validate.HomeSubjectParam;
import com.mdd.coupon.vo.HomeSubjectListVo;
import com.mdd.coupon.vo.HomeSubjectDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】服务接口类
 */
public interface IHomeSubjectService extends IService<HomeSubject> {

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<HomeSubjectVo>
     */
    PageResult<HomeSubjectListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】详情
     *
     * @param id 主键ID
     * @return HomeSubject
     */
    HomeSubjectDetailVo detail(Long id);

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】新增
     *
     * @param homeSubjectParam 参数
     */
    void add(HomeSubjectParam homeSubjectParam);

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】编辑
     *
     * @param homeSubjectParam 参数
     */
    void edit(HomeSubjectParam homeSubjectParam);

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
