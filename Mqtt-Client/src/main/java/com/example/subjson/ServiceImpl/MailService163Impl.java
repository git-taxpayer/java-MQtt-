package com.example.subjson.ServiceImpl;

import com.example.subjson.Service.MailService;
import com.example.subjson.po.EmailConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


@Service
    public class MailService163Impl implements MailService {
        private final JavaMailSender mailSender;
        private final EmailConfig emailConfig;


        public MailService163Impl(JavaMailSender mailSender, EmailConfig emailConfig) {
            this.mailSender = mailSender;
            this.emailConfig = emailConfig;
        }

        @Override
        public boolean sendMail(String to, String subject, String text) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(emailConfig.getFrom());
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(text);
            try {

                mailSender.send(msg);
            } catch (MailException ex) {
                System.err.println(ex.getMessage());
                System.out.println("根据上述错误提示改动");
                return false;
            }
            return true;
        }

    @Override
    public boolean sendMailWithAttachment(String to, String subject, String text, String attachmentFilePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailConfig.getFrom());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            // 添加附件
            FileSystemResource file = new FileSystemResource(new File(attachmentFilePath));
            helper.addAttachment("output.xlsx", file);
            mailSender.send(message);
        } catch (MessagingException | MailException e) {
            System.err.println(e.getMessage()+"666666");
            return false;
        }
        return true;
    }
}

