package com.ruolan.o2o.service;

import com.ruolan.o2o.BaseTest;
import com.ruolan.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductCategoryServiceTest extends BaseTest {

    @Autowired
    ProductCategoryService service;

    @Test
    public void testQueryProductCategoryList(){
        long shopId = 20L;
        List<ProductCategory> categoryList = service.getProductCategoryList(shopId);
        System.out.println("个数是：" + categoryList.size());

    }

}
