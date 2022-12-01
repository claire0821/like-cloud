package com.mdd.search.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.es.SkuEsModel;
import com.mdd.search.service.IProductSaveService;
import com.mdd.search.vo.ProductRelatedInfo;
import com.mdd.search.vo.ProductSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-29 13:41
 **/
@RestController
@RequestMapping("api/search/save")
public class SaveController {
    @Autowired
    IProductSaveService iProductSaveService;


    @Log(title = "上架商品")
    @PostMapping("/product")
    public Object productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {
        try {
            iProductSaveService.productStatusUp(skuEsModels);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.success();
    }

}
