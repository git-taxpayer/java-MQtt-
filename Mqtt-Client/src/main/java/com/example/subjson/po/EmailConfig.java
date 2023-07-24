package com.example.subjson.po;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmailConfig {

    @Value("${mail163.from}")
    private String from;

    @Value("${mail163.password}")
    private String password;


    // Getter and Setter methods...
}

