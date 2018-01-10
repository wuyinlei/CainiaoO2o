package com.ruolan.o2o.web.shopadmin;


import com.ruolan.o2o.entity.ProductCategory;
import com.ruolan.o2o.entity.Shop;
import com.ruolan.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        if (currentShop == null){
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

}
