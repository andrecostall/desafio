package com.codegroup.desafio.service;

import com.codegroup.desafio.enums.Risco;
import com.codegroup.desafio.enums.Status;
import com.codegroup.desafio.model.PessoaModel;
import com.codegroup.desafio.model.ProjetoModel;
import com.codegroup.desafio.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final PessoaService pessoaService;

    public ProjetoService(ProjetoRepository projetoRepository, PessoaService pessoaService) {
        this.projetoRepository = projetoRepository;
        this.pessoaService = pessoaService;
    }

    public List<ProjetoModel> listarTodosProjetos() {
        return this.projetoRepository.findAll();
    }

    public Optional<ProjetoModel> encontrarProjetoPorId(Long id) {
        return Optional.ofNullable(this.projetoRepository.findById(id).get());
    }

    public ProjetoModel salvarProjeto(ProjetoModel projetoModel) {
        if (projetoModel.getMembros() != null && !projetoModel.getMembros().isEmpty()) {
            for(int i = 0; i < projetoModel.getMembros().size(); ++i) {
                Optional<PessoaModel> pessoa = Optional.ofNullable(projetoModel.getMembros().get(i));
                pessoa = this.pessoaService.encontrarPessoaPorId((pessoa.get()).getId());
                if (pessoa.isPresent() && !pessoa.get().getFuncionario()) {
                    throw new IllegalArgumentException((pessoa.get()).getNome() + " não é funcionário(a).");
                }
                projetoModel.getMembros().set(i, pessoa.get());
            }
        }

        if (projetoModel.getOrcamento() != null) {
            if ((double)projetoModel.getOrcamento() <= 100000.0) {
                projetoModel.setRisco(String.valueOf(Risco.BAIXA.getDescricao()));
            } else if ((double)projetoModel.getOrcamento() <= 500000.0) {
                projetoModel.setRisco(String.valueOf(Risco.MEDIA.getDescricao()));
            } else {
                projetoModel.setRisco(String.valueOf(Risco.ALTO.getDescricao()));
            }
        }

        if (projetoModel.getStatus() == null || !this.StatusProjetoValido(projetoModel.getStatus())) {
            projetoModel.setStatus(Status.EM_ANALISE);
        }

        return this.projetoRepository.save(projetoModel);
    }

    public ProjetoModel atualizarProjeto(ProjetoModel projetoModel) {
        return this.projetoRepository.save(projetoModel);
    }

    public void excluirProjeto(ProjetoModel projetoModel) throws IllegalArgumentException {
        Optional<ProjetoModel> projetoOptional = this.projetoRepository.findById(projetoModel.getId());
        if (projetoOptional.isPresent()) {
            if (!this.podeExcluirProjeto(((ProjetoModel)projetoOptional.get()).getStatus())) {
                throw new IllegalStateException("Não é permitido excluir um projeto com status iniciado, em andamento ou encerrado.");
            }

            this.projetoRepository.deleteById(projetoModel.getId());
        }

    }

    // Método auxiliar para verificar se o status do projeto é válido
    private boolean StatusProjetoValido(Status status) {
        Status[] statusValidos = {
                Status.EM_ANALISE,
                Status.ANALISE_REALIZADA,
                Status.ANALISE_APROVADA,
                Status.INICIADO,
                Status.PLANEJADO,
                Status.EM_ANDAMENTO,
                Status.ENCERRADO,
                Status.CANCELADO
        };

        for (Status statusValido : statusValidos) {
            if (status == statusValido) {
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para verificar se é permitido excluir o projeto com base no status
    private boolean podeExcluirProjeto(Status status) {
        return !Status.INICIADO.equals(status) &&
                !Status.EM_ANDAMENTO.equals(status) &&
                !Status.ENCERRADO.equals(status);
    }
}
