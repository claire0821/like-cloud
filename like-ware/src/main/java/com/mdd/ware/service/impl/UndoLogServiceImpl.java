package com.mdd.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.ware.service.IUndoLogService;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.validate.UndoLogParam;
import com.mdd.ware.vo.UndoLogListVo;
import com.mdd.ware.vo.UndoLogDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.ware.entity.UndoLog;
import com.mdd.ware.mapper.UndoLogMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 【请填写功能名称】实现类
 */
@Service
public class UndoLogServiceImpl extends ServiceImpl<UndoLogMapper,UndoLog> implements IUndoLogService {
        
    @Resource
    UndoLogMapper undoLogMapper;

    /**
     * 【请填写功能名称】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UndoLogListVo>
     */
    @Override
    public PageResult<UndoLogListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<UndoLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        undoLogMapper.setSearch(queryWrapper, params, new String[]{
            "=:branchId@branch_id:long",
            "=:xid:str",
            "=:context:str",
            "=:rollbackInfo@rollback_info:str",
            "=:logStatus@log_status:int",
            "=:logCreated@log_created:str",
            "=:logModified@log_modified:str",
            "=:ext:str",
        });

        IPage<UndoLog> iPage = undoLogMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<UndoLogListVo> list = new LinkedList<>();
        for(UndoLog item : iPage.getRecords()) {
            UndoLogListVo vo = new UndoLogListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 【请填写功能名称】详情
     *
     * @param id 主键参数
     * @return UndoLog
     */
    @Override
    public UndoLogDetailVo detail(Long id) {
        UndoLog model = undoLogMapper.selectOne(
                new QueryWrapper<UndoLog>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        UndoLogDetailVo vo = new UndoLogDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 【请填写功能名称】新增
     *
     * @param undoLogParam 参数
     */
    @Override
    public void add(UndoLogParam undoLogParam) {
        UndoLog model = new UndoLog();
        model.setBranchId(undoLogParam.getBranchId());
        model.setXid(undoLogParam.getXid());
        model.setContext(undoLogParam.getContext());
        model.setRollbackInfo(undoLogParam.getRollbackInfo());
        model.setLogStatus(undoLogParam.getLogStatus());
        model.setLogCreated(undoLogParam.getLogCreated());
        model.setLogModified(undoLogParam.getLogModified());
        model.setExt(undoLogParam.getExt());
        undoLogMapper.insert(model);
    }

    /**
     * 【请填写功能名称】编辑
     *
     * @param undoLogParam 参数
     */
    @Override
    public void edit(UndoLogParam undoLogParam) {
        UndoLog model = undoLogMapper.selectOne(
                new QueryWrapper<UndoLog>()
                    .eq("id",  undoLogParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(undoLogParam.getId());
        model.setBranchId(undoLogParam.getBranchId());
        model.setXid(undoLogParam.getXid());
        model.setContext(undoLogParam.getContext());
        model.setRollbackInfo(undoLogParam.getRollbackInfo());
        model.setLogStatus(undoLogParam.getLogStatus());
        model.setLogCreated(undoLogParam.getLogCreated());
        model.setLogModified(undoLogParam.getLogModified());
        model.setExt(undoLogParam.getExt());
        undoLogMapper.updateById(model);
    }

    /**
     * 【请填写功能名称】删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        UndoLog model = undoLogMapper.selectOne(
                new QueryWrapper<UndoLog>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        undoLogMapper.delete(new QueryWrapper<UndoLog>().eq("id", id));
    }

}
