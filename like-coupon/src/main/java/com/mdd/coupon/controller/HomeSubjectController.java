package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.IHomeSubjectService;
import com.mdd.coupon.validate.HomeSubjectParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.HomeSubjectListVo;
import com.mdd.coupon.vo.HomeSubjectDetailVo;
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
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】管理
 */
@RestController
@RequestMapping("api/coupon/subject")
public class HomeSubjectController {

    @Resource
    IHomeSubjectService iHomeSubjectService;

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<HomeSubjectListVo> list = iHomeSubjectService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        HomeSubjectDetailVo detail = iHomeSubjectService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】新增
     *
     * @param homeSubjectParam 参数
     * @return Object
     */
    @Log(title = "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】新增")
    @PostMapping("/add")
    public Object add(@Validated(value = HomeSubjectParam.create.class) @RequestBody HomeSubjectParam homeSubjectParam) {
        iHomeSubjectService.add(homeSubjectParam);
        return AjaxResult.success();
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】编辑
     *
     * @param homeSubjectParam 参数
     * @return Object
     */
    @Log(title = "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = HomeSubjectParam.update.class) @RequestBody HomeSubjectParam homeSubjectParam) {
        iHomeSubjectService.edit(homeSubjectParam);
        return AjaxResult.success();
    }


    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iHomeSubjectService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
