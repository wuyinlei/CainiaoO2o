package com.ruolan.o2o.dao;

import com.ruolan.o2o.entity.Shop;

public interface ShopDao {

    /**
     * 增加店铺
     *
     * @param shop Shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop Shop
     * @return
     */
    int updateShop(Shop shop);

}
