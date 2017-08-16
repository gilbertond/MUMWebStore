/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.amqpServer;

/**
 *
 * @author gilberto
 */
public class AmqpServiceImpl implements AmqpService {

    @Override
    public String message() {
        return "Sup gilbert: AMQP message";
    }
}
