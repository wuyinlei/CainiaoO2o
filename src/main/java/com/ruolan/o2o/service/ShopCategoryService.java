package com.ruolan.o2o.service;

import com.ruolan.o2o.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {

    List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);

    ShopCategory queryShopCategoryById(Long shopCategoryId);

}
