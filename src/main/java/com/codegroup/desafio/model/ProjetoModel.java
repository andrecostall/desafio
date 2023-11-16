package com.codegroup.desafio.model;


import com.codegroup.desafio.enums.Status;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projeto")
public class ProjetoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Column(name = "data_inicio")
    private Date dataInicio;
    @Column(name = "data_previsao_fim")
    private Date dataPrevisaoFim;
    @Column(name = "data_fim")
    private Date dataFim;
    @Column(name = "descricao", length = 5000)
    private String descricao;
    @Column(name = "status", length = 45)
    private Status status;
    @Column(name = "orcamento")
    private Float orcamento;
    @Column(name = "risco", length = 45)
    private String risco;

    @ManyToOne
    @JoinColumn(name = "idgerente")
    private PessoaModel gerente;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "membros",
            joinColumns = @JoinColumn(name = "idprojeto",referencedColumnName= "id"),
            inverseJoinColumns = @JoinColumn(name = "idpessoa",referencedColumnName= "id"))
    private List<PessoaModel> membros;

    public ProjetoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataPrevisaoFim() {
        return dataPrevisaoFim;
    }

    public void setDataPrevisaoFim(Date dataPrevisaoFim) {
        this.dataPrevisaoFim = dataPrevisaoFim;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Float getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Float orcamento) {
        this.orcamento = orcamento;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(String risco) {
        this.risco = risco;
    }

    public PessoaModel getGerente() {
        return gerente;
    }

    public void setGerente(PessoaModel gerente) {
        this.gerente = gerente;
    }

    public List<PessoaModel> getMembros() {
        return membros;
    }

    public void setMembros(List<PessoaModel> membros) {
        this.membros = membros;
    }

    public ProjetoModel(Long id, String nome, Date dataInicio, Date dataPrevisaoFim, Date dataFim, String descricao, Status status, Float orcamento, String risco, PessoaModel gerente, List<PessoaModel> membros) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataPrevisaoFim = dataPrevisaoFim;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.status = status;
        this.orcamento = orcamento;
        this.risco = risco;
        this.gerente = gerente;
        this.membros = membros;
    }
}
