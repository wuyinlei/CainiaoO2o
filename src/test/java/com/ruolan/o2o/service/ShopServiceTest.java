package com.ruolan.o2o.service;

import com.ruolan.o2o.BaseTest;
import com.ruolan.o2o.entity.Area;
import com.ruolan.o2o.entity.Shop;
import com.ruolan.o2o.entity.ShopCategory;
import com.ruolan.o2o.enums.ShopStateEnum;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


public class ShopServiceTest extends BaseTest {

    @Autowired
    ShopService shopService;



	@Test
	@Ignore
    public void testShopService(){

        Shop shop = new Shop();
		shop.setOwnerId(8L);
		Area area = new Area();
		area.setAreaId(3L);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(17L);
		shop.setShopName("mytest1");
		shop.setShopDesc("mytest1");
		shop.setShopAddr("testaddr1");
		shop.setPhone("13810524526");
		shop.setShopImg("test1");
		shop.setLongitude(1D);
		shop.setLatitude(1D);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		shop.setArea(area);
		shop.setShopCategory(sc);
//        FileItem fileItem = FileIt
//        CommonsMultipartFile file = new CommonsMultipartFile()
//		ShopExecution se = shopService.addShop(shop);
//		assertEquals("mytest1", se.getShop().getShopName());

    }

}
