package com.ruolan.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruolan.o2o.dto.ShopExecution;
import com.ruolan.o2o.entity.Area;
import com.ruolan.o2o.entity.PersonInfo;
import com.ruolan.o2o.entity.Shop;
import com.ruolan.o2o.entity.ShopCategory;
import com.ruolan.o2o.enums.ProductCategoryStateEnum;
import com.ruolan.o2o.enums.ShopStateEnum;
import com.ruolan.o2o.service.AreaService;
import com.ruolan.o2o.service.ShopCategoryService;
import com.ruolan.o2o.service.ShopService;
import com.ruolan.o2o.utils.CodeUtil;
import com.ruolan.o2o.utils.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;
//    @Autowired
//    private LocalAuthService localAuthService;


    /**
     * 获取初始化的店铺的信息
     * @return
     */
    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList;
        List<Area> areaList;
        try {
            //获取到全部的商店列表
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            //获取到所有的区域信息
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }

        return modelMap;
    }


    /**
     * 注册店铺
     * @param request
     * @return
     */
    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        MultipartHttpServletRequest multipartRequest = null;
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            multipartRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartRequest
                    .getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        try {
            shop = mapper.readValue(shopStr, Shop.class);
//            shop = mapper.
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        if (shop != null && shopImg != null) {
            try {
//                PersonInfo user = new PersonInfo();
//                user.setUserId(9L);
//                shop.setOwner(user);
//                shop.setOwnerId(user.getUserId());
                PersonInfo user = (PersonInfo) request.getSession()
                        .getAttribute("user");
                shop.setOwnerId(user.getUserId());
                ShopExecution se = shopService.addShop(shop, shopImg);
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    // 若shop创建成功，则加入session中，作为权限使用
                    @SuppressWarnings("unchecked")
                    List<Shop> shopList = (List<Shop>) request.getSession()
                            .getAttribute("shopList");
                    if (shopList != null && shopList.size() > 0) {
                        shopList.add(se.getShop());
                        request.getSession().setAttribute("shopList", shopList);
                    } else {
                        shopList = new ArrayList<>();
                        shopList.add(se.getShop());
                        request.getSession().setAttribute("shopList", shopList);
                    }
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
        }
        return modelMap;
    }


    @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopById(@RequestParam Long shopId,
                                            HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();
        if (shopId != null && shopId > -1) {
            Shop shop = shopService.getByShopId(shopId);
//            shop.getShopCategory().setShopCategoryName(
//                    shopCategoryService.getShopCategoryById(
//                            shop.getShopCategory().getShopCategoryId())
//                            .getShopCategoryName());
//            shop.getParentCategory().setShopCategoryName(
//                    shopCategoryService.getShopCategoryById(
//                            shop.getParentCategory().getShopCategoryId())
//                            .getShopCategoryName());

            modelMap.put("shop", shop);
            request.getSession().setAttribute("currentShop", "shop");
            List<Area> areaList = new ArrayList<Area>();
            try {
                areaList = areaService.getAreaList();
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;

    }


    /**
     * 修改店铺信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyShop(HttpServletRequest request) {
        boolean statusChange = HttpServletRequestUtil.getBoolean(request,
                "statusChange");
        Map<String, Object> modelMap = new HashMap<>();
        if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        MultipartHttpServletRequest multipartRequest = null;
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            multipartRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartRequest
                    .getFile("shopImg");
        }
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        // TODO: 2018/1/10 获取的session    这个地方还没有获取到  肯定为空的
//        Shop currentShop = (Shop) request.getSession().getAttribute(
//                "currentShop");
//        shop.setShopId(currentShop.getShopId());
        if (shop != null && shop.getShopId() != null) {
            filterAttribute(shop);
            try {
                ShopExecution se = shopService.modifyShop(shop, shopImg);
                if (se.getState() == ProductCategoryStateEnum.SUCCESS
                        .getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
        }
        return modelMap;
    }

    private void filterAttribute(Shop shop) {

    }


    @RequestMapping(value = "/getshopmanagementinfo" ,method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopManagementInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        if (shopId <= 0){
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null){
                modelMap.put("redirect",true);
                modelMap.put("url","/shop/shoplist");
            } else {
                Shop currentShop = (Shop) currentShopObj;
                modelMap.put("redirect",false);
                modelMap.put("shopId",currentShop.getShopId());
            }
        } else {
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop",currentShop);
            modelMap.put("redirect",false);
        }
        return modelMap;
    }

    /**
     * 获取店铺列表(根据具体的需要这个需要在ShopDao中完善查询出来的信息)
     * @param request
     * @return
     */
    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> list(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        PersonInfo user =new PersonInfo();
        request.getSession().setAttribute("user",user);
        user = (PersonInfo) request.getSession()
                .getAttribute("user");

        user.setUserId(8L);
        user.setName("若兰明月");
        Long employeeId = user.getUserId();
        List<Shop> shopList = new ArrayList<>();
        try {
            Shop shopCondation = new Shop();
            shopCondation.setOwner(user);
            //个人创建的店铺 比较少  这里不做分页
            ShopExecution se = shopService.getShopList(shopCondation,0,100);
            modelMap.put("shopList",se.getShopList());
            modelMap.put("user",user);
            modelMap.put("count",se.getCount());
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }

        return modelMap;
    }

}
