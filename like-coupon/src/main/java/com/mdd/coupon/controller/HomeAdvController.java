package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.IHomeAdvService;
import com.mdd.coupon.validate.HomeAdvParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.HomeAdvListVo;
import com.mdd.coupon.vo.HomeAdvDetailVo;
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
 * 首页轮播广告管理
 */
@RestController
@RequestMapping("api/coupon/adv")
public class HomeAdvController {

    @Resource
    IHomeAdvService iHomeAdvService;

    /**
     * 首页轮播广告列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<HomeAdvListVo> list = iHomeAdvService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 首页轮播广告详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        HomeAdvDetailVo detail = iHomeAdvService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 首页轮播广告新增
     *
     * @param homeAdvParam 参数
     * @return Object
     */
    @Log(title = "首页轮播广告新增")
    @PostMapping("/add")
    public Object add(@Validated(value = HomeAdvParam.create.class) @RequestBody HomeAdvParam homeAdvParam) {
        iHomeAdvService.add(homeAdvParam);
        return AjaxResult.success();
    }

    /**
     * 首页轮播广告编辑
     *
     * @param homeAdvParam 参数
     * @return Object
     */
    @Log(title = "首页轮播广告编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = HomeAdvParam.update.class) @RequestBody HomeAdvParam homeAdvParam) {
        iHomeAdvService.edit(homeAdvParam);
        return AjaxResult.success();
    }


    /**
     * 首页轮播广告批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "首页轮播广告批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iHomeAdvService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
