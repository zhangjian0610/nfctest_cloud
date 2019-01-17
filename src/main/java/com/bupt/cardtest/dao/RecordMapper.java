package com.bupt.cardtest.dao;

import com.bupt.cardtest.model.bean.Record;
import com.bupt.cardtest.model.bean.RecordExample;
import com.bupt.cardtest.model.bean.RecordKey;
import java.util.List;

import com.bupt.cardtest.model.pagebean.RecordPage;
import org.apache.ibatis.annotations.Param;

public interface RecordMapper {
    long countByExample(RecordExample example);

    int deleteByExample(RecordExample example);

    int deleteByPrimaryKey(RecordKey key);

    int insert(Record record);

    int insertSelective(Record record);

    List<Record> selectByExample(RecordExample example);

    Record selectByPrimaryKey(RecordKey key);

    int updateByExampleSelective(@Param("record") Record record, @Param("example") RecordExample example);

    int updateByExample(@Param("record") Record record, @Param("example") RecordExample example);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    /**
     * 通过Record 查找文件下所有记录
     *
     * */
    List<RecordPage> dataGrid(Record record);

    /**
     * 通过Record 查找 统计数目
     *
     * */
    Long countDataGrid(Record record);
}