package com.estoquepreco.controller;

import com.estoquepreco.services.RabbitMQService;
import constantes.RabbitMQConstantes;
import dto.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estoque")
public class EstoqueController {

    @Autowired
    private RabbitMQService service;

    @PutMapping
    private ResponseEntity alteraEstoque(@RequestBody EstoqueDTO dto) {
        service.publicarMensagem(RabbitMQConstantes.FILA_ESTOQUE, dto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
