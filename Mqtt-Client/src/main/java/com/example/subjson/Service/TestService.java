package com.example.subjson.Service;


import com.example.subjson.po.test;

import java.util.Date;
import java.util.List;

public interface TestService {
    void saveDataFromJson(test entity);

     List<test> getAllTestData() ;

     test getOldestTest();

    int getOrdinalCount();

    void deleteDataByDate(Date date );

    Date getEarliestDate();

    // Date getEarliestDate();
    //void createTempTable();


}
