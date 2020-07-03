package com.chouxiaozi.mailserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@SpringBootTest
class MailserverApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    @Test
    void contextLoads() {
    }
    @Test
    public void sendThymeleafMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("1065970342@qq.com");
        helper.setTo("455193135@qq.com");
        helper.setCc("1980592746@qq.com");
        helper.setBcc("843338961@qq.com");
        helper.setSentDate(new Date());
        Context context = new Context();
        context.setVariable("name", "李文谨");
        context.setVariable("posName","架构师");
        context.setVariable("joblevelName", "高级架构师");
        context.setVariable("departmentName", "研发总部");
        String process = templateEngine.process("mail.html", context);
        helper.setText(process,true);
        javaMailSender.send(mimeMessage);
    }

}
