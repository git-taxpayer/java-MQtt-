package com.example.subjson.Controller;
import com.example.subjson.Paho.ExcelConverter;
import com.example.subjson.Service.TestService;
import com.example.subjson.Service.TopicService;
import com.example.subjson.po.Topic;
import com.example.subjson.po.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class excelController {
    private final TestService testService ;
    private final ExcelConverter excelConverter;
    private final TopicService topicService;
    @Autowired
    public excelController(TestService testService, ExcelConverter excelConverter,TopicService topicService) {
        this.testService = testService;
        this.excelConverter = excelConverter;
        this.topicService=topicService;
    }

    @GetMapping("/exportToExcel")
    public void exportToExcel() {
       // List<test> dataList = testService.getAllTestData();
      //  excelConverter.convertToExcel(dataList);
    }
    @GetMapping("look")
    public List<Topic> LookTopic(){
        List<Topic> topicList = topicService.getAllTopics();
        return topicList;
    }
}
