package com.example.subjson.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.subjson.po.Topic;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface TopicMapper {

    @Select("select * from topic")
    List<Topic> getAllTopics();

    void createTable(@Param("tableName") String tableName);

}
