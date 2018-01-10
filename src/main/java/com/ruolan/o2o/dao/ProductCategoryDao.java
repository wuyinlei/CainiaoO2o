package com.ruolan.o2o.dao;

import com.ruolan.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {


    /**
     * 通过shopid 查询店铺商品类别
     *
     * @param shopId 店铺id
     * @return List<ProductCategory>
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

}
