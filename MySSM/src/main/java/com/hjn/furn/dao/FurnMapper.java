package com.hjn.furn.dao;

import com.hjn.furn.bean.Furn;
import com.hjn.furn.bean.FurnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FurnMapper {
    long countByExample(FurnExample example);

    int deleteByExample(FurnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Furn record);

    int insertSelective(Furn record);

    //返回所有的对象
    List<Furn> selectByExample(FurnExample example);

    Furn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Furn record, @Param("example") FurnExample example);

    int updateByExample(@Param("record") Furn record, @Param("example") FurnExample example);

    int updateByPrimaryKeySelective(Furn record);

    int updateByPrimaryKey(Furn record);
}