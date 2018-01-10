package com.ruolan.o2o.service;

import com.ruolan.o2o.dto.ShopExecution;
import com.ruolan.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface ShopService {

    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);

    /**
     * 查询指定店铺信息
     *
     * @param shopId shopId
     * @return Shop shop
     */
    Shop getByShopId(long shopId);


    /**
     * 返回总数
     * @return
     */
    int queryShopCount(Shop shopCondition);


    /**
     * 更新店铺信息（从店家角度）
     *
     * @return
     * @throws RuntimeException
     */
    ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;


    /**
     * 分页查询店铺,可输入的条件有：店铺名（模糊），店铺状态，店铺Id,店铺类别,区域ID
     *
     * @param shopCondition 输入条件
     * @param pageIndex     第几页
     * @param pageSize      每页数据
     * @return
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

}
