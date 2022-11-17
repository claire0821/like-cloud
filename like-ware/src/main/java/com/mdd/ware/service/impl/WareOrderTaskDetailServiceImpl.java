package com.mdd.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.ware.service.IWareOrderTaskDetailService;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.validate.WareOrderTaskDetailParam;
import com.mdd.ware.vo.WareOrderTaskDetailListVo;
import com.mdd.ware.vo.WareOrderTaskDetailDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.ware.entity.WareOrderTaskDetail;
import com.mdd.ware.mapper.WareOrderTaskDetailMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 库存工作单实现类
 */
@Service
public class WareOrderTaskDetailServiceImpl extends ServiceImpl<WareOrderTaskDetailMapper,WareOrderTaskDetail> implements IWareOrderTaskDetailService {
        
    @Resource
    WareOrderTaskDetailMapper wareOrderTaskDetailMapper;

    /**
     * 库存工作单列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<WareOrderTaskDetailListVo>
     */
    @Override
    public PageResult<WareOrderTaskDetailListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<WareOrderTaskDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        wareOrderTaskDetailMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "like:skuName@sku_name:str",
            "=:skuNum@sku_num:int",
            "=:taskId@task_id:long",
            "=:wareId@ware_id:long",
            "=:lockStatus@lock_status:int",
        });

        IPage<WareOrderTaskDetail> iPage = wareOrderTaskDetailMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<WareOrderTaskDetailListVo> list = new LinkedList<>();
        for(WareOrderTaskDetail item : iPage.getRecords()) {
            WareOrderTaskDetailListVo vo = new WareOrderTaskDetailListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 库存工作单详情
     *
     * @param id 主键参数
     * @return WareOrderTaskDetail
     */
    @Override
    public WareOrderTaskDetailDetailVo detail(Long id) {
        WareOrderTaskDetail model = wareOrderTaskDetailMapper.selectOne(
                new QueryWrapper<WareOrderTaskDetail>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        WareOrderTaskDetailDetailVo vo = new WareOrderTaskDetailDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 库存工作单新增
     *
     * @param wareOrderTaskDetailParam 参数
     */
    @Override
    public void add(WareOrderTaskDetailParam wareOrderTaskDetailParam) {
        WareOrderTaskDetail model = new WareOrderTaskDetail();
        model.setSkuId(wareOrderTaskDetailParam.getSkuId());
        model.setSkuName(wareOrderTaskDetailParam.getSkuName());
        model.setSkuNum(wareOrderTaskDetailParam.getSkuNum());
        model.setTaskId(wareOrderTaskDetailParam.getTaskId());
        model.setWareId(wareOrderTaskDetailParam.getWareId());
        model.setLockStatus(wareOrderTaskDetailParam.getLockStatus());
        wareOrderTaskDetailMapper.insert(model);
    }

    /**
     * 库存工作单编辑
     *
     * @param wareOrderTaskDetailParam 参数
     */
    @Override
    public void edit(WareOrderTaskDetailParam wareOrderTaskDetailParam) {
        WareOrderTaskDetail model = wareOrderTaskDetailMapper.selectOne(
                new QueryWrapper<WareOrderTaskDetail>()
                    .eq("id",  wareOrderTaskDetailParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(wareOrderTaskDetailParam.getId());
        model.setSkuId(wareOrderTaskDetailParam.getSkuId());
        model.setSkuName(wareOrderTaskDetailParam.getSkuName());
        model.setSkuNum(wareOrderTaskDetailParam.getSkuNum());
        model.setTaskId(wareOrderTaskDetailParam.getTaskId());
        model.setWareId(wareOrderTaskDetailParam.getWareId());
        model.setLockStatus(wareOrderTaskDetailParam.getLockStatus());
        wareOrderTaskDetailMapper.updateById(model);
    }

    /**
     * 库存工作单删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        WareOrderTaskDetail model = wareOrderTaskDetailMapper.selectOne(
                new QueryWrapper<WareOrderTaskDetail>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        wareOrderTaskDetailMapper.delete(new QueryWrapper<WareOrderTaskDetail>().eq("id", id));
    }

}
