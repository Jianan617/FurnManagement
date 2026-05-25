package com.hjn.furn.test;

import com.hjn.furn.bean.Furn;
import com.hjn.furn.dao.FurnMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class FurnMapperTest {
    private FurnMapper furnMapper;
    @Before
    public void init(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        furnMapper = ioc.getBean(FurnMapper.class);
        System.out.println("furnMapper = " + furnMapper);
    }
    
    @Test
    public void insertSelectiveTest(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        FurnMapper furnMapper = ioc.getBean(FurnMapper.class);
        System.out.println("furnMapper = " + furnMapper);
        Furn furn = new Furn(null,"北欧风格沙发~","蚂蚁家居",new BigDecimal(180),666,7,"assets/images/sofa.jpg");
        int affectedRows = furnMapper.insertSelective(furn);
        System.out.println("affected rows = " + affectedRows);
        System.out.println("==========操作成功==========");
    }
    
    @Test
    public void deleteByPrimaryKeyTest(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        FurnMapper furnMapper = ioc.getBean(FurnMapper.class);
        System.out.println("furnMapper = " + furnMapper);
        int affectedRows = furnMapper.deleteByPrimaryKey(6);
        System.out.println("affected rows = " + affectedRows);
        System.out.println("==========删除成功==========");
    }
    
    @Test
    public void updateByPrimaryKeySelectiveTest(){
        Furn furn = new Furn();
        furn.setId(5);
        furn.setName("北欧风格沙发~");
        int affectedRows = furnMapper.updateByPrimaryKeySelective(furn);
        System.out.println("affected rows = " + affectedRows);
        System.out.println("==========更新成功==========");
    }
    
    @Test
    public void selectByPrimaryKeyTest(){
        Furn furn = furnMapper.selectByPrimaryKey(1);
        System.out.println("furn: " + furn);
        System.out.println("==========查询成功==========");
    }
}
