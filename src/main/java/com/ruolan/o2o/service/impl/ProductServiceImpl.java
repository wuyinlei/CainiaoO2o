package com.ruolan.o2o.service.impl;

import com.ruolan.o2o.dao.ProductDao;
import com.ruolan.o2o.entity.Product;
import com.ruolan.o2o.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProductById(long productId) {
        return productDao.queryProductByProductId(productId);
    }
}
