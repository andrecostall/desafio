package com.codegroup.desafio.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record PessoaDto(
        Long id,
        String nome,
        Date datanascimento,
        String cpf,
        Boolean funcionario,
        String atribuicao
        ) {
}
