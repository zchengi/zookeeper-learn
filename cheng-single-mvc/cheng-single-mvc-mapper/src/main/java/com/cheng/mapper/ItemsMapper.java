package com.cheng.mapper;

import com.cheng.pojo.Items;
import com.cheng.pojo.ItemsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cheng
 *         2018/10/3 12:47
 */
public interface ItemsMapper {
    int countByExample(ItemsExample example);

    int deleteByExample(ItemsExample example);

    int deleteByPrimaryKey(String id);

    int insert(Items record);

    int insertSelective(Items record);

    List<Items> selectByExample(ItemsExample example);

    Items selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Items record, @Param("example") ItemsExample example);

    int updateByExample(@Param("record") Items record, @Param("example") ItemsExample example);

    int updateByPrimaryKeySelective(Items record);

    int updateByPrimaryKey(Items record);
    
    int reduceCounts(Items record);
}