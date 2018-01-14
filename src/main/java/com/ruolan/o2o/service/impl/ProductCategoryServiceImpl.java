package com.ruolan.o2o.service.impl;

import com.ruolan.o2o.dao.ProductCategoryDao;
import com.ruolan.o2o.dao.ProductDao;
import com.ruolan.o2o.dto.ProductCategoryExecution;
import com.ruolan.o2o.entity.ProductCategory;
import com.ruolan.o2o.enums.ProductCategoryStateEnum;
import com.ruolan.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    /**
     * 插入多个店铺商品信息
     *
     * @param productCategoryList
     * @return
     * @throws RuntimeException
     */
    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws RuntimeException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectedNum = productCategoryDao
                        .batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new RuntimeException("店铺类别失败");
                } else {

                    return new ProductCategoryExecution(
                            ProductCategoryStateEnum.SUCCESS);
                }

            } catch (Exception e) {
                throw new RuntimeException("batchAddProductCategory error: "
                        + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(
                    ProductCategoryStateEnum.INNER_ERROR);
        }
    }

    /**
     * 删除店铺里面的商品并且置productCategoryId为null
     *
     * @param productCategoryId productCategoryId
     * @param shopId  shopId
     * @return
     * @throws RuntimeException
     */
    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws RuntimeException {
        try {
            int effectedNum = productDao
                    .updateProductCategoryToNull(productCategoryId);
            if (effectedNum < 0) {
                throw new RuntimeException("商品类别更新失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("deleteProductCategory error: "
                    + e.getMessage());
        }
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(
                    productCategoryId, shopId);
            if (effectedNum <= 0) {
                throw new RuntimeException("店铺类别删除失败");
            } else {
                return new ProductCategoryExecution(
                        ProductCategoryStateEnum.SUCCESS);
            }

        } catch (Exception e) {
            throw new RuntimeException("deleteProductCategory error: "
                    + e.getMessage());
        }
    }
}
