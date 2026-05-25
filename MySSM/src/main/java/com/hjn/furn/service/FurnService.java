package com.hjn.furn.service;

import com.hjn.furn.bean.Furn;

import java.util.List;

public interface FurnService {
    //添加家具
    public void save(Furn furn);
    //查询所有家具
    public List<Furn> findAllFurns();
    //按id查询指定家具
    public Furn findFurnById(Integer id);
    //更新家具
    public void update(Furn furn);
    //删除家具
    public void delete(Integer id);
    //根据家具名称查询
    public List<Furn> findFurnByCondition(String name);
}
