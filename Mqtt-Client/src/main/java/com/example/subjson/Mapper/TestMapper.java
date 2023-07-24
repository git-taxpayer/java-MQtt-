package com.example.subjson.Mapper;
import com.example.subjson.po.test;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
    public interface TestMapper {

        @Insert("INSERT INTO test (ordinal, date, Product_name, results, state, data01, data02, data03, data04, data05, data06, data07, data08, data09, data10, data11, data12, data13, data14, data15, data16, data17, data18, data19, data20, data21, data22, data23, data24, data25, data26, data27, data28, data29, data30, data31, data32, data33, data34, data35, data36, data37, data38, data39, data40, data41, data42, data43, data44, data45, data46, data47, data48, data49, data50) " +
                "VALUES (#{ordinal}, #{date}, #{productName}, #{results}, #{state}, #{data01}, #{data02}, #{data03}, #{data04}, #{data05}, #{data06}, #{data07}, #{data08}, #{data09}, #{data10}, #{data11}, #{data12}, #{data13}, #{data14}, #{data15}, #{data16}, #{data17}, #{data18}, #{data19}, #{data20}, #{data21}, #{data22}, #{data23}, #{data24}, #{data25}, #{data26}, #{data27}, #{data28}, #{data29}, #{data30}, #{data31}, #{data32}, #{data33}, #{data34}, #{data35}, #{data36}, #{data37}, #{data38}, #{data39}, #{data40}, #{data41}, #{data42}, #{data43}, #{data44}, #{data45}, #{data46}, #{data47}, #{data48}, #{data49}, #{data50})")
        void insertData(test entity);

        @Select("select * from test")
        List<test> getAllTestData();

//        @Delete("DELETE FROM test WHERE date = (SELECT MIN(date) FROM test)")
//        void deleteEarliestData();

        @Select("SELECT MIN(date) FROM test")
        Date getEarliestDate();

        @Delete("DELETE FROM test WHERE date = #{date}")
        void deleteDataByDate(Date date);

        @Select("SELECT COUNT(*) FROM test")
        int getOrdinalCount();

        @Select("SELECT * FROM test ORDER BY ordinal ASC LIMIT 1")
        test getOldestTest();

//        @Update("CREATE TEMPORARY TABLE temp_table AS (SELECT * FROM test ORDER BY date ASC LIMIT 1)")
//        void createTempTable();
}

