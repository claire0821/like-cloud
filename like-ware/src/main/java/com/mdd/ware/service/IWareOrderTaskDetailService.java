package com.mdd.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.entity.WareOrderTaskDetail;
import com.mdd.ware.validate.WareOrderTaskDetailParam;
import com.mdd.ware.vo.WareOrderTaskDetailListVo;
import com.mdd.ware.vo.WareOrderTaskDetailDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 库存工作单服务接口类
 */
public interface IWareOrderTaskDetailService extends IService<WareOrderTaskDetail> {

    /**
     * 库存工作单列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<WareOrderTaskDetailVo>
     */
    PageResult<WareOrderTaskDetailListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 库存工作单详情
     *
     * @param id 主键ID
     * @return WareOrderTaskDetail
     */
    WareOrderTaskDetailDetailVo detail(Long id);

    /**
     * 库存工作单新增
     *
     * @param wareOrderTaskDetailParam 参数
     */
    void add(WareOrderTaskDetailParam wareOrderTaskDetailParam);

    /**
     * 库存工作单编辑
     *
     * @param wareOrderTaskDetailParam 参数
     */
    void edit(WareOrderTaskDetailParam wareOrderTaskDetailParam);

    /**
     * 库存工作单删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
