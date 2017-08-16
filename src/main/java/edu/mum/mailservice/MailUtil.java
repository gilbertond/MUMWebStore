package edu.mum.mailservice;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * Created by Hatake on 8/15/2017.
 */
@Component
public class MailUtil {
    private MailSender mailServer;

    public MailUtil(MailSender mailServer) {
        super();
        this.mailServer = mailServer;
    }

    public void sendEmail(String toAddress, String fromAddress,
                          String subject, String msgBody) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(msgBody);
        mailServer.send(message);
    }
}
