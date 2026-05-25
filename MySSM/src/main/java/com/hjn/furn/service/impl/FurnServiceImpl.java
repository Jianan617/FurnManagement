package com.hjn.furn.service.impl;

import com.hjn.furn.bean.Furn;
import com.hjn.furn.bean.FurnExample;
import com.hjn.furn.dao.FurnMapper;
import com.hjn.furn.service.FurnService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class FurnServiceImpl implements FurnService {
    @Resource
    private FurnMapper furnMapper;
    
    //添加家具
    @Override
    public void save(Furn furn) {
        furnMapper.insertSelective(furn);
    }
    
    //查询所有家具
    @Override
    public List<Furn> findAllFurns() {
        return furnMapper.selectByExample(null);
    }
    
    //按id查询指定家具
    public Furn findFurnById(Integer id){
        return furnMapper.selectByPrimaryKey(id);
    }
    
    //更新家具
    @Override
    public void update(Furn furn) {
        furnMapper.updateByPrimaryKeySelective(furn);
    }
    
    //删除家具
    @Override
    public void delete(Integer id) {
        furnMapper.deleteByPrimaryKey(id);
    }
    
    //根据家具名称查询
    @Override
    public List<Furn> findFurnByCondition(String name) {
        //FurnExample可以带很多条件查询
        FurnExample furnExample = new FurnExample();
        //通过Criteria可以设置查询条件
        FurnExample.Criteria criteria = furnExample.createCriteria();
        if(StringUtils.hasText(name)){
            criteria.andNameLike("%" + name + "%");
        }
        //如果name为空，会查询所有的furn
        return furnMapper.selectByExample(furnExample);
    }
}
