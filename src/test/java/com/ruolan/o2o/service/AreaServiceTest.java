package com.ruolan.o2o.service;

import com.ruolan.o2o.BaseTest;
import com.ruolan.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void testAreaService(){

        List<Area> areaList = areaService.getAreaList();
        for (Area area : areaList) {
            System.out.println(area.getAreaDesc());
        }

    }

}
