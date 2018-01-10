package com.ruolan.o2o.service;

import com.ruolan.o2o.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductById(long productId);

}
