package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.IHomeSubjectSpuService;
import com.mdd.coupon.validate.HomeSubjectSpuParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.HomeSubjectSpuListVo;
import com.mdd.coupon.vo.HomeSubjectSpuDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 专题商品管理
 */
@RestController
@RequestMapping("api/coupon/spu")
public class HomeSubjectSpuController {

    @Resource
    IHomeSubjectSpuService iHomeSubjectSpuService;

    /**
     * 专题商品列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<HomeSubjectSpuListVo> list = iHomeSubjectSpuService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 专题商品详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        HomeSubjectSpuDetailVo detail = iHomeSubjectSpuService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 专题商品新增
     *
     * @param homeSubjectSpuParam 参数
     * @return Object
     */
    @Log(title = "专题商品新增")
    @PostMapping("/add")
    public Object add(@Validated(value = HomeSubjectSpuParam.create.class) @RequestBody HomeSubjectSpuParam homeSubjectSpuParam) {
        iHomeSubjectSpuService.add(homeSubjectSpuParam);
        return AjaxResult.success();
    }

    /**
     * 专题商品编辑
     *
     * @param homeSubjectSpuParam 参数
     * @return Object
     */
    @Log(title = "专题商品编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = HomeSubjectSpuParam.update.class) @RequestBody HomeSubjectSpuParam homeSubjectSpuParam) {
        iHomeSubjectSpuService.edit(homeSubjectSpuParam);
        return AjaxResult.success();
    }


    /**
     * 专题商品批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "专题商品批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iHomeSubjectSpuService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
