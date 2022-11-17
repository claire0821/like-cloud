package com.mdd.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.entity.UndoLog;
import com.mdd.ware.validate.UndoLogParam;
import com.mdd.ware.vo.UndoLogListVo;
import com.mdd.ware.vo.UndoLogDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 【请填写功能名称】服务接口类
 */
public interface IUndoLogService extends IService<UndoLog> {

    /**
     * 【请填写功能名称】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UndoLogVo>
     */
    PageResult<UndoLogListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 【请填写功能名称】详情
     *
     * @param id 主键ID
     * @return UndoLog
     */
    UndoLogDetailVo detail(Long id);

    /**
     * 【请填写功能名称】新增
     *
     * @param undoLogParam 参数
     */
    void add(UndoLogParam undoLogParam);

    /**
     * 【请填写功能名称】编辑
     *
     * @param undoLogParam 参数
     */
    void edit(UndoLogParam undoLogParam);

    /**
     * 【请填写功能名称】删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
