package com.chouxiaozi.mailserver.rabbitmq;

import com.chouxiaozi.vhr.model.Employee;
import com.chouxiaozi.vhr.model.MailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class EmployeeReceive {

    Logger logger = LoggerFactory.getLogger(Employee.class);

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;
    @Autowired
    TemplateEngine templateEngine;

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void getMsg(Message msg){
        Employee employee = (Employee) msg.getPayload();
        logger.info("receive:===> "+msg);
        try {
            sendMail(employee);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("邮件发送失败：" + e.getMessage());
        }
    }

    private void sendMail(Employee employee) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("入职欢迎");
        helper.setFrom(mailProperties.getUsername());
        helper.setTo(employee.getEmail());
        helper.setSentDate(new Date());
        Context context = new Context();
        context.setVariable("name", employee.getName());
        context.setVariable("posName",employee.getPosition().getName());
        context.setVariable("joblevelName", employee.getJobLevel().getName());
        context.setVariable("departmentName", employee.getDepartment().getName());
        String process = templateEngine.process("mail", context);
        helper.setText(process,true);
        javaMailSender.send(mimeMessage);
        logger.info(employee.getName()+":邮件发送成功");
    }
}
