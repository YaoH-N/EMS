package com.nyh.mapper;

import com.nyh.pojo.Eadmin;
import com.nyh.pojo.EadminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EadminMapper {
    long countByExample(EadminExample example);

    int deleteByExample(EadminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Eadmin record);

    int insertSelective(Eadmin record);

    List<Eadmin> selectByExample(EadminExample example);

    Eadmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Eadmin record, @Param("example") EadminExample example);

    int updateByExample(@Param("record") Eadmin record, @Param("example") EadminExample example);

    int updateByPrimaryKeySelective(Eadmin record);

    int updateByPrimaryKey(Eadmin record);
}