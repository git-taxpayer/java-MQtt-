package com.example.subjson.Paho;

import com.example.subjson.ServiceImpl.EmailSenderQQService;
import com.example.subjson.ServiceImpl.MailService163Impl;
import com.example.subjson.po.test;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class ExcelConverter {
   private final EmailSenderQQService emailSenderService;
    private final MailService163Impl mailService163;

    @Autowired
    public ExcelConverter(EmailSenderQQService emailSenderService,
                          MailService163Impl mailService163) {
        this.emailSenderService = emailSenderService;
        this.mailService163 = mailService163;
    }
     // 获取项目根目录
    String projectRoot = System.getProperty("user.dir");
    // 构建完整的文件路径
    String filePath = projectRoot + "/output.xlsx";

    public void convertToExcel(List<test> dataList) {
        // 获取用户主目录和桌面路径
//        String userHome = System.getProperty("user.home");
//        String desktopPath = userHome + "/Desktop";
        // 调用转换方法，传递桌面路径作为参数
        convertToExcel(dataList, filePath);

    }


    public void convertToExcel(List<test> dataList, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // 获取属性名称
        Class<?> clazz = test.class;
        Field[] fields = clazz.getDeclaredFields();
        String[] headerNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            headerNames[i] = fields[i].getName();
        }

        // 创建表头
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headerNames.length; i++) {
            headerRow.createCell(i).setCellValue(headerNames[i]);
        }

        // 填充数据
        int rowNum = 1;
        for (test testData : dataList) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < headerNames.length; i++) {
                try {
                    Field field = clazz.getDeclaredField(headerNames[i]);
                    field.setAccessible(true);
                    Object value = field.get(testData);
                    if (value != null) {
                        row.createCell(i).setCellValue(value.toString());
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // 调整列宽以适应内容
        for (int i = 0; i < headerNames.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // 保存 Excel 文件
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String attachmentFilePath = filePath; // 使用生成的 Excel 文件路径作为附件路径
        sendEmail163WithAttachment(attachmentFilePath);
        //sendEmailWithAttachment(attachmentFilePath);
    }
    private void sendEmailWithAttachment(String attachmentFilePath) {
        String recipient = "1627910948@qq.com";
        String subject = "报表";
        String body = "测试";

        emailSenderService.sendEmailWithAttachment(recipient, subject, body, attachmentFilePath);
        sendEmail163WithAttachment(attachmentFilePath);
    }
    private void sendEmail163WithAttachment(String attachmentFilePath) {
        String to = "yuwen_zhou@163.com";
        String subject = "报表:(163)";
        String body = "baobiao";
       // System.out.println(attachmentFilePath);

      // mailService163.sendMail(to, subject, body);
       mailService163.sendMailWithAttachment(to,subject,body,attachmentFilePath);
    }

    @Scheduled(cron = "0 0 * * * *")
    private void sendEmailtodingshi(){

    }
}
