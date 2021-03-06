package com.ruolan.o2o.service.impl;

import com.ruolan.o2o.dao.ShopCategoryDao;
import com.ruolan.o2o.dao.ShopDao;
import com.ruolan.o2o.dto.ShopExecution;
import com.ruolan.o2o.entity.Shop;
import com.ruolan.o2o.entity.ShopCategory;
import com.ruolan.o2o.enums.ShopStateEnum;
import com.ruolan.o2o.service.ShopService;
import com.ruolan.o2o.utils.FileUtil;
import com.ruolan.o2o.utils.ImageUtil;
import com.ruolan.o2o.utils.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    /**
     * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
     * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     */
    @Transactional
    @Override
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {
        //
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
        }

        try {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            if (shop.getShopCategory() != null) {
                Long shopCategoryId = shop.getShopCategory()
                        .getShopCategoryId();
                ShopCategory sc = shopCategoryDao.queryShopCategoryById(shopCategoryId);
                ShopCategory parentCategory = new ShopCategory();
                parentCategory.setShopCategoryId(sc.getParentId());
//                shop.setParentCategory(parentCategory);
            }
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new RuntimeException("店铺创建失败");
            } else {
                try {
                    if (shopImg != null) {
                        addShopImg(shop, shopImg);
                        effectedNum = shopDao.updateShop(shop);
                        if (effectedNum <= 0) {
                            throw new RuntimeException("创建图片地址失败");
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException("addShopImg error: "
                            + e.getMessage());
                }


            }


        } catch (Exception e) {
            throw new RuntimeException("addShop error" + e.getMessage());
        }

        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public int queryShopCount(Shop shopCondition) {
        return shopDao.queryShopCount(shopCondition);
    }

    @Transactional
    @Override
    public ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException {

        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP_ID);
        } else {
            try {
                if (shopImg != null) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        FileUtil.deleteFile(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImg);
                }
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {// 创建成功
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }

            }catch (Exception e){
                throw new RuntimeException("modifyShop error: "
                        + e.getMessage());
            }
        }
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {

        ShopExecution se = new ShopExecution();

        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition,rowIndex,pageSize);
        int count = shopDao.queryShopCount(shopCondition);

        if (shopList != null){
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }

        return se;
    }

    /**
     * 添加图片地址
     *
     * @param shop    Shop
     * @param shopImg shopImg
     */
    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        //获取shop图片的相对值路径
        String dest = FileUtil.getShopImagePath(shop.getShopId());
        // 存储图片   获取图片相对路径
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgAddr);
    }
}
