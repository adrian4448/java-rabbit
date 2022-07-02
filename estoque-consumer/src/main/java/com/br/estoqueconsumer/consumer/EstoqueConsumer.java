package com.br.estoqueconsumer.consumer;

import constantes.RabbitMQConstantes;
import dto.EstoqueDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueConsumer {

    @RabbitListener(queues = RabbitMQConstantes.FILA_ESTOQUE)
    private void estoqueListener(EstoqueDTO dto) {
        System.out.println(dto.codigoProduto);
        System.out.println(dto.quantidade);
        System.out.println("--------------------------");
    }

}
