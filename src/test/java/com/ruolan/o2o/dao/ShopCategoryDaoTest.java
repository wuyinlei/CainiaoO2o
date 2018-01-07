package com.ruolan.o2o.dao;

import com.ruolan.o2o.BaseTest;
import com.ruolan.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.TabExpander;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory(){

        ShopCategory testCategory = new ShopCategory();
//        ShopCategory shopCategory = new ShopCategory();
//        shopCategory.setParentId(10L);
        testCategory.setParentId(10L);

        List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategory(testCategory);

        assertEquals(2,shopCategories.size());


    }

}
