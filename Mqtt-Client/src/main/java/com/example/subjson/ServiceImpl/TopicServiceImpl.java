package com.example.subjson.ServiceImpl;

import com.example.subjson.Mapper.TopicMapper;
import com.example.subjson.Service.TopicService;
import com.example.subjson.po.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private TopicMapper topicMapper;

    @Autowired
    public TopicServiceImpl(TopicMapper topicMapper){
        this.topicMapper = topicMapper;
    }

    @Override
    public List<Topic> getAllTopics() {
      return   topicMapper.getAllTopics();
    }
}
