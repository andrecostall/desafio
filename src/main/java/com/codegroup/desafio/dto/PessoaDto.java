package com.codegroup.desafio.dto;

import com.codegroup.desafio.model.PessoaModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {
        private Long id;
        @NotBlank(message = "O nome da pessoa é obrigatario")
        private String nome;
        @NotNull(message = "A data de nascimento da pessoa é obrigatario")
        private Date datanascimento;
        private String cpf;
        private Boolean funcionario;
        @NotBlank(message = "A atribuição(cargo) da pessoa é obrigatario")
        private String atribuicao;

}
