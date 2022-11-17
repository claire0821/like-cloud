package com.mdd.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.ware.service.IWareInfoService;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.validate.WareInfoParam;
import com.mdd.ware.vo.WareInfoListVo;
import com.mdd.ware.vo.WareInfoDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.ware.entity.WareInfo;
import com.mdd.ware.mapper.WareInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 仓库信息实现类
 */
@Service
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper,WareInfo> implements IWareInfoService {
        
    @Resource
    WareInfoMapper wareInfoMapper;

    /**
     * 仓库信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<WareInfoListVo>
     */
    @Override
    public PageResult<WareInfoListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<WareInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        wareInfoMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:address:str",
            "=:areacode:str",
        });

        IPage<WareInfo> iPage = wareInfoMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<WareInfoListVo> list = new LinkedList<>();
        for(WareInfo item : iPage.getRecords()) {
            WareInfoListVo vo = new WareInfoListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 仓库信息详情
     *
     * @param id 主键参数
     * @return WareInfo
     */
    @Override
    public WareInfoDetailVo detail(Long id) {
        WareInfo model = wareInfoMapper.selectOne(
                new QueryWrapper<WareInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        WareInfoDetailVo vo = new WareInfoDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 仓库信息新增
     *
     * @param wareInfoParam 参数
     */
    @Override
    public void add(WareInfoParam wareInfoParam) {
        WareInfo model = new WareInfo();
        model.setName(wareInfoParam.getName());
        model.setAddress(wareInfoParam.getAddress());
        model.setAreacode(wareInfoParam.getAreacode());
        wareInfoMapper.insert(model);
    }

    /**
     * 仓库信息编辑
     *
     * @param wareInfoParam 参数
     */
    @Override
    public void edit(WareInfoParam wareInfoParam) {
        WareInfo model = wareInfoMapper.selectOne(
                new QueryWrapper<WareInfo>()
                    .eq("id",  wareInfoParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(wareInfoParam.getId());
        model.setName(wareInfoParam.getName());
        model.setAddress(wareInfoParam.getAddress());
        model.setAreacode(wareInfoParam.getAreacode());
        wareInfoMapper.updateById(model);
    }

    /**
     * 仓库信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        WareInfo model = wareInfoMapper.selectOne(
                new QueryWrapper<WareInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        wareInfoMapper.delete(new QueryWrapper<WareInfo>().eq("id", id));
    }

}
