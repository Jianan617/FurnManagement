package com.hjn.furn.test.service;

import com.hjn.furn.bean.Furn;
import com.hjn.furn.service.FurnService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class ServiceTest {
    private ApplicationContext ioc;
    private FurnService furnService;
    
    @Before
    public void init(){
        ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        furnService = ioc.getBean(FurnService.class);
        System.out.println("furnService = " + furnService.getClass());
    }
    
    @Test
    public void saveTest(){
        Furn furn = new Furn(null,"北欧风格沙发~~","蚂蚁家居",new BigDecimal(180),666,7,"assets/images/sofa.jpg");
        furnService.save(furn);
    }
    
    @Test
    public void findAllTest(){
        List<Furn> furns= furnService.findAllFurns();
        for (Furn furn : furns) {
            System.out.println(furn);
        }
    }
    
    @Test
    public void updateFurnTest(){
        Furn furn = new Furn(16,"北欧风格沙发~~","蚂蚁家居",new BigDecimal(180),666,7,"assets/images/sofa.jpg");
        furnService.update(furn);
    }
    
    @Test
    public void findFurnByConditionTest(){
        List<Furn> furnList = furnService.findFurnByCondition("北欧");
        for (Furn furn : furnList) {
            System.out.println(furn);
        }
    }
}
