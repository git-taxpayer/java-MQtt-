package com.example.subjson.Service;

import org.springframework.web.multipart.MultipartFile;

public interface MailService {
    public boolean sendMail(String to, String subject, String text);
    public boolean sendMailWithAttachment(String to, String subject, String text, String filePath) ;


}
