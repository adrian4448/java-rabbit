package com.estoquepreco.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publicarMensagem(String nomeFila, Object mensagem) {
        rabbitTemplate.convertAndSend(nomeFila, mensagem);
    }
}
