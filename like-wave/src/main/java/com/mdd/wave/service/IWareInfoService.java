package com.mdd.wave.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.entity.WareInfo;
import com.mdd.wave.validate.WareInfoParam;
import com.mdd.wave.vo.WareInfoListVo;
import com.mdd.wave.vo.WareInfoDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 仓库信息服务接口类
 */
public interface IWareInfoService extends IService<WareInfo> {

    /**
     * 仓库信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<WareInfoVo>
     */
    PageResult<WareInfoListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 仓库信息详情
     *
     * @param id 主键ID
     * @return WareInfo
     */
    WareInfoDetailVo detail(Long id);

    /**
     * 仓库信息新增
     *
     * @param wareInfoParam 参数
     */
    void add(WareInfoParam wareInfoParam);

    /**
     * 仓库信息编辑
     *
     * @param wareInfoParam 参数
     */
    void edit(WareInfoParam wareInfoParam);

    /**
     * 仓库信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
