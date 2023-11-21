package com.codegroup.desafio.dto;

import com.codegroup.desafio.enums.Status;
import com.codegroup.desafio.model.PessoaModel;
import com.codegroup.desafio.model.ProjetoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProjetoDto{
    private Long id;
    @NotBlank( message = "O nome do projeto é obrigatório")
    private  String nome;
    @NotNull( message = "A Data Inicio do Projeto é obrigatório")
    private Date dataInicio;
    @NotNull( message = "A Data Previsao Fim do Projeto é obrigatório")
    private Date dataPrevisaoFim;
    @NotNull( message = "A Data Fim Projeto é obrigatório")
    private Date dataFim;
    private String descricao;
    private Status status;
    @NotNull( message = "O orçamento é obrigatório")
    private Float orcamento;
    private String risco;
    @NotNull( message = "O idGerente é obrigatório")
    private PessoaModel gerente;
    private List<PessoaModel> membros;

}
