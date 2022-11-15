package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.ISpuInfoService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.SpuInfoParam;
import com.mdd.product.vo.SpuInfoListVo;
import com.mdd.product.vo.SpuInfoDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.SpuInfo;
import com.mdd.product.mapper.SpuInfoMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import com.mdd.product.vo.SpuSaveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * spu信息实现类
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper,SpuInfo> implements ISpuInfoService {

    @Resource
    SpuInfoMapper spuInfoMapper;

    /**
     * spu信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SpuInfoListVo>
     */
    @Override
    public PageResult<SpuInfoListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        spuInfoMapper.setSearch(queryWrapper, params, new String[]{
                "like:spuName@spu_name:str",
                "=:spuDescription@spu_description:str",
                "=:catalogId@catalog_id:long",
                "=:brandId@brand_id:long",
                "=:weight:str",
                "=:publishStatus@publish_status:int",
        });

        IPage<SpuInfo> iPage = spuInfoMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SpuInfoListVo> list = new LinkedList<>();
        for(SpuInfo item : iPage.getRecords()) {
            SpuInfoListVo vo = new SpuInfoListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            vo.setUpdateTime(simpleDateFormat.format(item.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * spu信息详情
     *
     * @param id 主键参数
     * @return SpuInfo
     */
    @Override
    public SpuInfoDetailVo detail(Long id) {
        SpuInfo model = spuInfoMapper.selectOne(
                new QueryWrapper<SpuInfo>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SpuInfoDetailVo vo = new SpuInfoDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * spu信息新增
     *
     * @param spuInfoParam 参数
     */
    @Override
    public void add(SpuInfoParam spuInfoParam) {
        SpuInfo model = new SpuInfo();
        model.setSpuName(spuInfoParam.getSpuName());
        model.setSpuDescription(spuInfoParam.getSpuDescription());
        model.setCatalogId(spuInfoParam.getCatalogId());
        model.setBrandId(spuInfoParam.getBrandId());
        model.setWeight(spuInfoParam.getWeight());
        model.setPublishStatus(spuInfoParam.getPublishStatus());
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        spuInfoMapper.insert(model);
    }

    /**
     * spu信息编辑
     *
     * @param spuInfoParam 参数
     */
    @Override
    public void edit(SpuInfoParam spuInfoParam) {
        SpuInfo model = spuInfoMapper.selectOne(
                new QueryWrapper<SpuInfo>()
                        .eq("id",  spuInfoParam.getId())
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(spuInfoParam.getId());
        model.setSpuName(spuInfoParam.getSpuName());
        model.setSpuDescription(spuInfoParam.getSpuDescription());
        model.setCatalogId(spuInfoParam.getCatalogId());
        model.setBrandId(spuInfoParam.getBrandId());
        model.setWeight(spuInfoParam.getWeight());
        model.setPublishStatus(spuInfoParam.getPublishStatus());
        model.setUpdateTime(new Date());
        model.setCreateTime(new Date());
        //TODO代码生成时间类型
        spuInfoMapper.updateById(model);
    }

    /**
     * spu信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SpuInfo model = spuInfoMapper.selectOne(
                new QueryWrapper<SpuInfo>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        spuInfoMapper.delete(new QueryWrapper<SpuInfo>().eq("id", id));
    }

    //TODO 高级部分完善
    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        //1、保存spu基本信息 pms_spu_info
        SpuInfo infoEntity = new SpuInfo();
        BeanUtils.copyProperties(vo,infoEntity);
        infoEntity.setCreateTime(new Date());
        infoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(infoEntity);

        //2、保存Spu的描述图片 pms_spu_info_desc
        //3、保存spu的图片集 pms_spu_images
        //4、保存spu的规格参数;pms_product_attr_value
        //5、保存spu的积分信息；gulimall_sms->sms_spu_bounds
        //5、保存当前spu对应的所有sku信息；
    }

    @Override
    public void saveBaseSpuInfo(SpuInfo spuInfo) {
        this.baseMapper.insert(spuInfo);
    }
}
