package com.ruolan.o2o.service;

import com.ruolan.o2o.dto.ShopExecution;
import com.ruolan.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface ShopService {

    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);

    /**
     * 查询指定店铺信息
     *
     * @param shopId
     *            shopId
     * @return Shop shop
     */
    Shop getByShopId(long shopId);


    /**
     * 更新店铺信息（从店家角度）
     *
     * @return
     * @throws RuntimeException
     */
    ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;



}
