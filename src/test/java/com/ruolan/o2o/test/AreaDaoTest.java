package com.ruolan.o2o.test;

import com.ruolan.o2o.BaseTest;
import com.ruolan.o2o.dao.AreaDao;
import com.ruolan.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaDaoTest extends BaseTest{

    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        for (Area area : areaList) {
            System.out.println(area.getAreaDesc());
        }

    }



}
