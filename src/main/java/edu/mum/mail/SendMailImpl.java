/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.mail;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
/**
 *
 * @author GNdenzi
 */
@Service("sendMailService")
public class SendMailImpl implements SendMailService{
    @Autowired
    private MailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Override
    public void sendMail(List<String> receptients, String email, String subject, List<String> ccs, String body){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setSentDate(new Date());
        msg.setSubject(subject);
        msg.setText(body);
        if (ccs != null) {
            String[] ccc = ccs.toArray(new String[0]);
            msg.setCc(ccc);            
        }
        if (receptients != null) {
            String[] receptient = receptients.toArray(new String[0]);
            msg.setTo(receptient);   
        }
        
        try{
            mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }    

    @Override
    public void sendMailFormatted(List<Address> receptients, String email, String subject, List<Address> cc, String body) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();            
            msg.setFrom(email);
            msg.setSentDate(new Date());
            msg.setSubject(subject);
            msg.setText(body);
            if (cc != null) {
                Address[] ccc = cc.toArray(new Address[0]);
                msg.setRecipients(Message.RecipientType.CC, ccc);
            }
            if (receptients != null) {
                Address[] receptient = receptients.toArray(new Address[0]);
                msg.setRecipients(Message.RecipientType.TO, receptient);
            }
            msg.setContent(body, "text/html; charset=utf-8");
            
            javaMailSender.send(msg);
            
        } catch (MessagingException ex) {
            Logger.getLogger(SendMailImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
