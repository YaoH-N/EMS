package com.nyh.mapper;

import com.nyh.pojo.Courier;
import com.nyh.pojo.CourierExample;
import java.util.List;

import com.nyh.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface CourierMapper {
    long countByExample(CourierExample example);

    int deleteByExample(CourierExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Courier record);

    int insertSelective(Courier record);

    List<Courier> selectByExample(CourierExample example);

    // 分页查询
    List<Courier> selectByLimit(@Param("offset") int offset, @Param("pageNumber") int pageNumber);

    Courier selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Courier record, @Param("example") CourierExample example);

    int updateByExample(@Param("record") Courier record, @Param("example") CourierExample example);

    int updateByPrimaryKeySelective(Courier record);

    int updateByPrimaryKey(Courier record);
}