package com.estoquepreco.conections;

import constantes.RabbitMQConstantes;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConection {

    private static final String NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange exchange() {
        return new DirectExchange(NOME_EXCHANGE, true, false);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE,
                troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adicionarFila() {
        Queue filaEstoque = this.fila(RabbitMQConstantes.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitMQConstantes.FILA_PRECO);

        DirectExchange troca = this.exchange();

        Binding ligacaoEstoque = relacionamento(filaEstoque, troca);
        Binding ligacaoPreco = relacionamento(filaPreco, troca);

        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);
    }
}
