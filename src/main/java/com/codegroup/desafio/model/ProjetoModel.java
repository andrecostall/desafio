package com.codegroup.desafio.model;


import com.codegroup.desafio.enums.Status;
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
@Table(name = "projeto")
public class ProjetoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "data_inicio")
    private Date dataInicio;
    @Column(name = "data_previsao_fim")
    private Date dataPrevisaoFim;
    @Column(name = "data_fim")
    private Date dataFim;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "status")
    private Status status;
    @Column(name = "orcamento")
    private Float orcamento;
    @Column(name = "risco")
    private String risco;

    @ManyToOne
    @JoinColumn(name = "idgerente")
    private PessoaModel gerente;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "membros",
            joinColumns = @JoinColumn(name = "idprojeto",referencedColumnName= "id"),
            inverseJoinColumns = @JoinColumn(name = "idpessoa",referencedColumnName= "id"))
    private List<PessoaModel> membros;

}
