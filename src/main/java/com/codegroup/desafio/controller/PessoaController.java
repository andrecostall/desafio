package com.codegroup.desafio.controller;

import com.codegroup.desafio.dto.PessoaDto;
import com.codegroup.desafio.model.PessoaModel;
import com.codegroup.desafio.service.PessoaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping({"/pessoa"})
    public ResponseEntity<List<PessoaModel>> listarProjetos() {
        List<PessoaModel> pessoaModel = this.pessoaService.listarTodasPessoas();
        return pessoaModel.isEmpty() ? new ResponseEntity(HttpStatus.NO_CONTENT) : new ResponseEntity(pessoaModel, HttpStatus.OK);
    }

    @PostMapping({"/pessoa"})
    public ResponseEntity<PessoaModel> salvarProjeto(@RequestBody PessoaDto pessoaDto) {
        PessoaModel pessoa = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.pessoaService.salvarPessoa(pessoa));
    }

    @GetMapping({"/pessoa/{id}"})
    public ResponseEntity encontrarPessoaPorId(@PathVariable("id") Long id) {
        Optional<PessoaModel> pessoa = this.pessoaService.encontrarPessoaPorId(id);
        return pessoa.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrado.") :
                ResponseEntity.status(HttpStatus.OK).body((PessoaModel)pessoa.get());
    }

    @PutMapping({"/pessoa/{id}"})
    public ResponseEntity atualizarPessoa(@PathVariable("id") Long id, @RequestBody PessoaDto pessoaDto) {
        Optional<PessoaModel> pessoa = this.pessoaService.encontrarPessoaPorId(id);
        if (pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrado.");
        } else {
            PessoaModel pessoaData = (PessoaModel)pessoa.get();
            BeanUtils.copyProperties(pessoaDto, pessoaData);
            return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.atualizarPessoa(pessoaData));
        }
    }

    @DeleteMapping({"/pessoa/{id}"})
    public ResponseEntity excluirPessoa(@PathVariable Long id) {
        Optional<PessoaModel> pessoa = this.pessoaService.encontrarPessoaPorId(id);
        if (pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrado.");
        } else {
            this.pessoaService.excluirPessoa((PessoaModel)pessoa.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Projeto deletado com sucesso.");
        }
    }
}
