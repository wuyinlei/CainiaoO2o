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



}
