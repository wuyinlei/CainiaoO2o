package com.ruolan.o2o.dto;

import com.ruolan.o2o.entity.Shop;
import com.ruolan.o2o.enums.ShopStateEnum;

import java.util.List;

/**
 * 店铺封装执行后结果
 */
public class ShopExecution {

    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    // 店铺数量
    private int count;

    // 操作的shop（增删改店铺的时候用）
    private Shop shop;

    // 获取的shop列表(查询店铺列表的时候用)
    private List<Shop> shopList;

    public ShopExecution() {
    }

    // 失败的构造器
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 成功的构造器  用于返回单个店铺
     *
     * @param stateEnum ShopStateEnum
     * @param shop      shop店铺
     */
    public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    /**
     * 成功的构造器  用于返回店铺的集合信息
     *
     * @param stateEnum ShopStateEnum
     * @param shopList  List<Shop></>
     */
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
