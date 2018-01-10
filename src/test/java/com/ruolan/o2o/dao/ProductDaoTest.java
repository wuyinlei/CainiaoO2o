package com.ruolan.o2o.dao;

import com.ruolan.o2o.BaseTest;
import com.ruolan.o2o.entity.Product;
import com.ruolan.o2o.entity.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest  extends BaseTest{


    @Autowired
    private ProductDao productDao;


    @Test
    public void testCQueryProductByProductId() throws Exception {
        long productId = 1;
//        ProductImg productImg1 = new ProductImg();
//        productImg1.setImgAddr("图片1");
//        productImg1.setImgDesc("测试图片1");
//        productImg1.setPriority(1);
//        productImg1.setCreateTime(new Date());
//        productImg1.setProductId(productId);
//        ProductImg productImg2 = new ProductImg();
//        productImg2.setImgAddr("图片2");
//        productImg2.setPriority(1);
//        productImg2.setCreateTime(new Date());
//        productImg2.setProductId(productId);
//        List<ProductImg> productImgList = new ArrayList<ProductImg>();
//        productImgList.add(productImg1);
//        productImgList.add(productImg2);
//        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
//        assertEquals(2, effectedNum);
//        Product product = productDao.queryProductByProductId(productId);
//        assertEquals(2, product.getProductImgList().size());
//        effectedNum = productImgDao.deleteProductImgByProductId(productId);
//        assertEquals(2, effectedNum);

        List<Product> product = productDao.queryProductByProductId(4L);
        System.out.println(product.get(0).getCreateTime());

    }


}
