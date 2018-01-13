package com.ruolan.o2o.dao;

import com.ruolan.o2o.entity.Product;

import java.util.List;

public interface ProductDao {


    /**
     *
     * @param productId
     * @return
     */
    List<Product> queryProductByProductId(long productId);


    /**
     * 删除商品类别之前，将商品类别ID置为空
     *
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);



}
