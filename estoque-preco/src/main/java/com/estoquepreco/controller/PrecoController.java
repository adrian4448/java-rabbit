package com.estoquepreco.controller;

import com.estoquepreco.services.RabbitMQService;
import constantes.RabbitMQConstantes;
import dto.PrecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("preco")
public class PrecoController {

    @Autowired
    private RabbitMQService service;

    @PutMapping
    private ResponseEntity alteraPreco(@RequestBody PrecoDTO dto) {
        service.publicarMensagem(RabbitMQConstantes.FILA_PRECO, dto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
