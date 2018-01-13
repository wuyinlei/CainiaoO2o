package com.ruolan.o2o.web.shopadmin;


import com.ruolan.o2o.dto.ProductCategoryExecution;
import com.ruolan.o2o.entity.ProductCategory;
import com.ruolan.o2o.entity.Shop;
import com.ruolan.o2o.enums.ProductCategoryStateEnum;
import com.ruolan.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {


    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getProductCategoryList(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();
        //其他进入这个界面之前已经传入了这个session
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if (currentShop == null) {
            currentShop = new Shop();
            currentShop.setShopId(20L);
        }
        List<ProductCategory> productCategories = null;
        if (currentShop != null && currentShop.getShopId() > 0) {
            productCategories = productCategoryService.getProductCategoryList(currentShop.getShopId());
            modelMap.put("data", productCategories);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    /**
     * 为某一个店铺批量添加商品类型
     *
     * @param productCategoryList 商品集合
     * @param request             request请求
     * @return
     */
    @RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProductCategorys(
            @RequestBody List<ProductCategory> productCategoryList,
            HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        //获取到当前的店铺id
        Shop currentShop = (Shop) request.getSession().getAttribute(
                "currentShop");
        //这个地方等开发完毕之后需要更改的
        // TODO: 2018/1/13
        if (currentShop == null) {
            currentShop = new Shop();
            currentShop.setShopId(20L);
        }
        for (ProductCategory pc : productCategoryList) {
            pc.setShopId(currentShop.getShopId());
        }
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                ProductCategoryExecution pe = productCategoryService
                        .batchAddProductCategory(productCategoryList);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS
                        .getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少输入一个商品类别");
        }
        return modelMap;
    }

    @RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> removeProductCategory(Long productCategoryId,
                                                      HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (productCategoryId != null && productCategoryId > 0) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute(
                        "currentShop");
                // TODO: 2018/1/13
                if (currentShop == null) {
                    currentShop = new Shop();
                    currentShop.setShopId(20L);
                }
                ProductCategoryExecution pe = productCategoryService
                        .deleteProductCategory(productCategoryId,
                                currentShop.getShopId());
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS
                        .getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少选择一个商品类别");
        }
        return modelMap;
    }

}
