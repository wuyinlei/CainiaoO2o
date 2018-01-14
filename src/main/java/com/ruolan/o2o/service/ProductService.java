package com.ruolan.o2o.service;

import com.ruolan.o2o.entity.Product;
import com.ruolan.o2o.exceptions.ProductExecution;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface ProductService {

    Product getProductById(long productId);


    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
    ProductExecution addProduct(Product product, CommonsMultipartFile thumbnail, List<CommonsMultipartFile> productImgs)
            throws RuntimeException;

    ProductExecution modifyProduct(Product product, CommonsMultipartFile thumbnail,
                                   List<CommonsMultipartFile> productImgs) throws RuntimeException;


}
