package com.codegroup.desafio.service;

import com.codegroup.desafio.model.PessoaModel;
import com.codegroup.desafio.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;


@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<PessoaModel> listarTodasPessoas() {
        return this.pessoaRepository.findAll();
    }

    public PessoaModel salvarPessoa(PessoaModel pessoaModel) {
        return (PessoaModel)this.pessoaRepository.save(pessoaModel);
    }

    public PessoaModel atualizarPessoa(PessoaModel pessoaModel) {
        return this.pessoaRepository.save(pessoaModel);
    }

    public void excluirPessoa(PessoaModel pessoaModel) {
        this.pessoaRepository.delete(pessoaModel);
    }

    public Optional<PessoaModel> encontrarPessoaPorId(Long id) {
        return this.pessoaRepository.findById(id);
    }
}
