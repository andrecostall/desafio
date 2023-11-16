package com.codegroup.desafio;

import com.codegroup.desafio.dto.PessoaDto;
import com.codegroup.desafio.model.PessoaModel;
import com.codegroup.desafio.service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private PessoaService pessoaService;
     PessoaModel pessoa;
    @BeforeAll
    public void iniciar() {
        pessoa = new PessoaModel();
        pessoa.setNome("Maria Oliveira");
        pessoa.setCpf("987.654.321-09");
        pessoa.setDatanascimento(Date.valueOf(LocalDate.of(1980, 01, 01)));
        pessoa.setAtribuicao("Desenvolvedor(a)");
        pessoa.setFuncionario(true);
        //this.pessoaService.salvarPessoa(pessoa);
    }

    @Test
    public void criarNovaPessoaTest() {
        HttpEntity<PessoaModel> httpEntity = new HttpEntity<>(this.pessoa);
        ResponseEntity<PessoaDto> response = this.testRestTemplate
                .exchange("/api/pessoa", HttpMethod.POST, httpEntity, PessoaDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().cpf(),"987.654.321-09");
    }

    @Test
    public void buscarTodasPessoasTest() {
        ResponseEntity<PessoaModel[]> response = this.testRestTemplate
                .exchange("/api/pessoa", HttpMethod.GET, null, PessoaModel[].class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void alterarPessoaTest() {

        PessoaModel pessoaData = this.pessoaService.salvarPessoa(this.pessoa);
        pessoaData.setFuncionario(false);
        pessoaData.setCpf("987.654.321-15");

        HttpEntity<PessoaModel> httpEntity = new HttpEntity<>(pessoaData);

        ResponseEntity<PessoaDto> response = this.testRestTemplate
                .exchange("/api/pessoa/" + pessoaData.getId(), HttpMethod.PUT, httpEntity, PessoaDto.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Assertions.assertEquals(response.getBody().funcionario(), false);
        Assertions.assertEquals(response.getBody().cpf(), "987.654.321-15");
    }

    @Test
    public void buscarPessoaPorIdTest() {

        PessoaModel pessoaData = this.pessoaService.salvarPessoa(this.pessoa);
        ResponseEntity<PessoaDto> response = this.testRestTemplate
                .exchange("/api/pessoa/" + pessoaData.getId(), HttpMethod.GET, null, PessoaDto.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void removerPessoaTest() {

        PessoaModel pessoaData = this.pessoaService.salvarPessoa(this.pessoa);

        ResponseEntity<Void> response = this.testRestTemplate
                .exchange("/api/pessoa/" + pessoaData.getId(), HttpMethod.DELETE, null, Void.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

}