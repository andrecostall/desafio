package com.codegroup.desafio.dto;

import com.codegroup.desafio.model.PessoaModel;

import java.util.Date;
import java.util.List;

public record PessoaDto(
        Long id,
        String nome,
        Date datanascimento,
        String cpf,
        Boolean funcionario
        ) {
}
