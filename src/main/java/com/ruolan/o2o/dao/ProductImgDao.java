package com.ruolan.o2o.dao;

import com.ruolan.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {


    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加商品详情图片
     *
     * @param productImgList List<ProductImg>
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 删除商品图片
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);
}
