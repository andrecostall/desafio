package com.codegroup.desafio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa")
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "datanascimento")
    private Date datanascimento;

    @Column(name = "cpf")
    private String cpf;

    @Column(name="funcionario")
    private Boolean funcionario;

    @Column(name="atribuicao")
    private String atribuicao;

}
