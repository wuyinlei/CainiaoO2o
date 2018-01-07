package com.ruolan.o2o.service;

import com.ruolan.o2o.dto.ShopExecution;
import com.ruolan.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface ShopService {

    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);

}