package com.mdd.common.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: server
 * @description: 购物项内容
 * @author: Claire
 * @create: 2022-12-06 10:34
 **/
@Data
public class CartItemVo {
    //spu
    private Long spuId;
    private String spuName;
    private String spuPic;
    private Long brandId;
    private String brandName;

    private Long categoryId;
    private String categoryName;
    //sku
    private Long skuId;
    private String skuName;
    private String skuPic;
    private BigDecimal skuPrice;
    private Integer skuQuantity;

    /**
     * 商品套餐属性
     */
    private List<String> skuAttrValues;
    private String saleValueStr;

    private BigDecimal totalPrice;



    private Boolean selected = true;

    /**
     * 商品状态
     */
    private Integer status;

    private Boolean showMemberPrice;
    private BigDecimal memberPrice;//会员价
    private BigDecimal activityPrice;//活动价


//    public Long getSkuId() {
//        return skuId;
//    }
//
//    public void setSkuId(Long skuId) {
//        this.skuId = skuId;
//    }
//
//    public Boolean getSelected() {
//        return selected;
//    }
//
//    public void setSelected(Boolean selected) {
//        this.selected = selected;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public List<String> getSkuAttrValues() {
//        return skuAttrValues;
//    }
//
//    public void setSkuAttrValues(List<String> skuAttrValues) {
//        this.skuAttrValues = skuAttrValues;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public Integer getCount() {
//        return count;
//    }
//
//    public void setCount(Integer count) {
//        this.count = count;
//    }

    /**
     * 计算当前购物项总价
     * @return
     */
    public BigDecimal getTotalPrice() {

        return this.skuPrice.multiply(new BigDecimal("" + this.skuQuantity));
    }

//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public String getSaleValueStr() {
//        return saleValueStr;
//    }
//
//    public void setSaleValueStr(String saleValueStr) {
//        this.saleValueStr = saleValueStr;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
}
