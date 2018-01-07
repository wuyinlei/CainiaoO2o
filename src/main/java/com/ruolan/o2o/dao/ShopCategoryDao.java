package com.ruolan.o2o.dao;

import com.ruolan.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryDao {

    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

    ShopCategory queryShopCategoryById(Long shopCategoryId);
}
