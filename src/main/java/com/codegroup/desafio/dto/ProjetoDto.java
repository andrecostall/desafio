package com.codegroup.desafio.dto;

import com.codegroup.desafio.enums.Status;
import com.codegroup.desafio.model.PessoaModel;

import java.util.Date;
import java.util.List;

public record ProjetoDto(
        Long id,
        String nome,
        Date dataInicio,
        Date dataPrevisaoFim,
        Date dataFim,
        String descricao,
        Status status,
        Float orcamento,
        String risco,
        PessoaModel gerente,
        List<PessoaModel> membros) {
}
