package com.mdd.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.entity.WareOrderTask;
import com.mdd.ware.validate.WareOrderTaskParam;
import com.mdd.ware.vo.WareOrderTaskListVo;
import com.mdd.ware.vo.WareOrderTaskDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 库存工作单服务接口类
 */
public interface IWareOrderTaskService extends IService<WareOrderTask> {

    /**
     * 库存工作单列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<WareOrderTaskVo>
     */
    PageResult<WareOrderTaskListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 库存工作单详情
     *
     * @param id 主键ID
     * @return WareOrderTask
     */
    WareOrderTaskDetailVo detail(Long id);

    /**
     * 库存工作单新增
     *
     * @param wareOrderTaskParam 参数
     */
    void add(WareOrderTaskParam wareOrderTaskParam);

    /**
     * 库存工作单编辑
     *
     * @param wareOrderTaskParam 参数
     */
    void edit(WareOrderTaskParam wareOrderTaskParam);

    /**
     * 库存工作单删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
