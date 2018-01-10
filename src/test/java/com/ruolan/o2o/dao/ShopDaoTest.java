package com.ruolan.o2o.dao;

import com.ruolan.o2o.BaseTest;
import com.ruolan.o2o.entity.Area;
import com.ruolan.o2o.entity.PersonInfo;
import com.ruolan.o2o.entity.Shop;
import com.ruolan.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {

    @Autowired
    ShopDao shopDao;

    @Test
    @Ignore
    public void testQueryShopById(){
        Shop shop = shopDao.queryByShopId(15L);
        System.out.println(shop.getShopId());
    }



    @Test
    @Ignore
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(8L);
        area.setAreaId(3L);
        shopCategory.setShopCategoryId(10L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("测试语句");
        shop.setShopAddr("测试");
        shop.setPhone("13718989054");
        shop.setShopImg("www.baidu.com");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);


    }

    @Test
    @Ignore
    public void  updateShop(){
        Shop shop = new Shop();
        shop.setShopId(16L);
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(8L);
        area.setAreaId(3L);
        shopCategory.setShopCategoryId(10L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺更改");
        shop.setShopDesc("测试语句更改");
        shop.setShopAddr("测试");
        shop.setPhone("13798789054");
        shop.setShopImg("http://www.baidu.com");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("已经通过");
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testBQueryShopList() throws Exception {
        Shop shopCondition = new Shop();
        PersonInfo info = new PersonInfo();
        info.setUserId(8l);
        List<Shop> shops = shopDao.queryShopList(shopCondition, 0, 5);

        System.out.println("员工号为8的人的店铺大小 ： " + shops.size());

        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺的总数 ： " + count);
    }

}
