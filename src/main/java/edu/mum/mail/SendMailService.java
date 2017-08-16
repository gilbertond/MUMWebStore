/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.mail;

import java.util.List;
import javax.mail.Address;

/**
 *
 * @author GNdenzi
 */
public interface SendMailService {
    /**
     * Send an email to a list of recipients
     * @param receptients
     * @param email
     * @param subject
     * @param cc 
     * @param body 
     */
    public void sendMail(List<String> receptients, String email, String subject, List<String> cc, String body);
    
    public void sendMailFormatted(List<Address> receptients, String email, String subject, List<Address> cc, String body);
}
