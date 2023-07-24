package com.example.subjson.ServiceImpl;

import com.example.subjson.Mapper.TestMapper;
import com.example.subjson.Service.TestService;
import com.example.subjson.po.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final TestMapper testMapper;

    @Autowired
    public TestServiceImpl(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @Override
    public void saveDataFromJson(test entity) {
        testMapper.insertData(entity);
    }
    @Override
    public List<test> getAllTestData() {
        return testMapper.getAllTestData();
    }

    @Override
    public void deleteDataByDate(Date date) {
        Date earliestDate = testMapper.getEarliestDate();
        if (earliestDate != null) {
            testMapper.deleteDataByDate(earliestDate);
        }
    }

    @Override
    public Date getEarliestDate() {
        return testMapper.getEarliestDate();
    }

    @Override
    public test getOldestTest() {
        return testMapper.getOldestTest();
    }

    @Override
    public int getOrdinalCount() {
        return testMapper.getOrdinalCount();
    }

}

//    @Override
//    public void createTempTable() {
//        testMapper.createTempTable();
//    }
