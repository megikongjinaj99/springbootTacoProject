package com.megiapp.springjwt.security.services;

import com.megiapp.springjwt.enums.EOrder;
import com.megiapp.springjwt.exception.EmailException;
import com.megiapp.springjwt.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    //Mail properties that are needed

    @Autowired
    private JavaMailSender sender;

    public void informManagerAboutOrder(Order newOrder) {
        log.info("Received request to inform Manager via email.");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("taco.store.manager@gmail.com");
        msg.setSubject(String.format(Templates.MAIL_TO_MANAGER_NEW_ORDER_SUBJECT, newOrder.getId()));
        msg.setText(parseInfoManagerMailBody(newOrder));
        log.info("Sending email.");
        try {
            sender.send(msg);
        } catch (Exception e) {
            log.error(String.format("Email exception: %s", e.getMessage()));
            throw new EmailException("Some thing went wrong while sending mail to inform Manger. Please see logs!");
        }
        log.info("Email sent successfully.");
    }

    public void informClientAboutOrderStatus(Order newOrder, String email) {
        log.info("Received request to inform client via email.");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email.trim());
        msg.setSubject(String.format(Templates.MAIL_TO_CLIENT_NEW_ORDER_SUBJECT, newOrder.getId()));
        msg.setText(parseClientMailBody(newOrder));
        log.info("Sending email.");
        try {
            sender.send(msg);
        } catch (Exception e) {
            log.error(String.format("Email exception: %s", e.getMessage()));
            throw new EmailException("Some thing went wrong while sending mail to inform Client. Please see logs!");
        }
        log.info("Email sent successfully.");
    }

    public void informClientAboutOrderStatusUpdate(Integer id, String email, EOrder newStatus) {
        log.info("Received request to inform client via email.");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email.trim());
        msg.setSubject(String.format(Templates.MAIL_TO_CLIENT_ORDER_DELIVERED_SUBJECT, id));
        msg.setText(parseClientUpdateStatusBody(id, newStatus));
        log.info("Sending email.");
        try {
            sender.send(msg);
        } catch (Exception e) {
            log.error(String.format("Email exception: %s", e.getMessage()));
            throw new EmailException("Some thing went wrong while sending mail to inform Client. Please see logs!");
        }
        log.info("Email sent successfully.");
    }

    private String parseInfoManagerMailBody(Order order) {
        String temp = Templates.MAIL_TO_MANAGER_NEW_ORDER_BODY
                .replace("{ORDER_TIME}", order.getPlacedAt().toString())
                .replace("{CLIENT_NAME}", (CharSequence) order.getU_users())
                .replace("{TACO_NAMES}", order.getTacos().toString())
                .replace("{TOTAL_PRICE}", String.valueOf(order.getTotal()))
                .replace("{ORDER_STATUS}", String.valueOf(order.getType()));

        System.out.println(temp);
        return temp;
    }

    private String parseClientMailBody(Order order) {
        return Templates.MAIL_TO_CLIENT_NEW_ORDER_BODY
                .replace("{ORDER_TIME}", order.getPlacedAt().toString())
                .replace("{TACO_NAMES}", order.getTacos().toString())
                .replace("{TOTAL_PRICE}", String.valueOf(order.getTotal()))
                .replace("{ORDER_STATUS}", String.valueOf(order.getType()));
    }

    private String parseClientUpdateStatusBody(Integer id, EOrder type) {
        return Templates.MAIL_TO_CLIENT_NEW_ORDER_BODY
                .replace("{ORDER_STATUS}", String.valueOf(type));
    }
}