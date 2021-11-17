package com.nyh.mapper;

import com.nyh.pojo.Express;
import com.nyh.pojo.ExpressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpressMapper {
    long countByExample(ExpressExample example);

    int deleteByExample(ExpressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Express record);

    int insertSelective(Express record);

    List<Express> selectByExample(ExpressExample example);

    // 分页查询
    List<Express> selectByLimit(@Param("offset") int offset, @Param("pageNumber") int pageNumber);

    Express selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Express record, @Param("example") ExpressExample example);

    int updateByExample(@Param("record") Express record, @Param("example") ExpressExample example);

    int updateByPrimaryKeySelective(Express record);

    int updateByPrimaryKey(Express record);
}