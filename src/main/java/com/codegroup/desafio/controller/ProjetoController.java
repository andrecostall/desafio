package com.codegroup.desafio.controller;

import com.codegroup.desafio.dto.ProjetoDto;
import com.codegroup.desafio.model.ProjetoModel;
import com.codegroup.desafio.service.ProjetoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProjetoController {
    private final ProjetoService projetoService;

    @GetMapping("/projeto")
    public ResponseEntity<List<ProjetoModel>> listarProjetos() {
        List<ProjetoModel> projetoModels = this.projetoService.listarTodosProjetos();
        if (projetoModels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projetoModels, HttpStatus.OK);
    }

    @PostMapping("/projeto")
    public ResponseEntity<ProjetoModel> salvarProjeto(@RequestBody @Valid ProjetoDto projetoDto) {
        var projeto = new ProjetoModel();
        BeanUtils.copyProperties(projetoDto, projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.projetoService.salvarProjeto(projeto));
    }

    @GetMapping("/projeto/{id}")
    public ResponseEntity encontrarProjetoPorId(@PathVariable("id") Long id) {
        Optional<ProjetoModel> projeto = this.projetoService.encontrarProjetoPorId(id);
        if (projeto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(projeto.get());
        }
    }

    @DeleteMapping("/projeto/{id}")
    public ResponseEntity excluirProjeto(@PathVariable("id") long id) {
        Optional<ProjetoModel> projeto = this.projetoService.encontrarProjetoPorId(id);
        if (projeto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado.");
        }
        this.projetoService.excluirProjeto(projeto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Projeto deletado com sucesso.");
    }

    @PutMapping("/projeto/{id}")
    public ResponseEntity atualizarProjeto(@PathVariable("id") long id, @RequestBody @Valid ProjetoDto projetoDto) {
        Optional<ProjetoModel> projeto = this.projetoService.encontrarProjetoPorId(id);
        if (projeto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado.");
        }
        var projetoData = projeto.get();
        BeanUtils.copyProperties(projetoDto, projetoData);
        return ResponseEntity.status(HttpStatus.OK).body(this.projetoService.atualizarProjeto(projetoData));
    }
}
