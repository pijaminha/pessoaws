package br.com.tech4me.pessoaws.View.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.tech4me.pessoaws.Shared.PessoaDTO;
import br.com.tech4me.pessoaws.View.Model.PessoaRequest;
import br.com.tech4me.pessoaws.View.Model.PessoaResponse;
import br.com.tech4me.pessoaws.model.Pessoa;
import br.com.tech4me.pessoaws.service.PessoaService;


@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    @Autowired
    PessoaService servico;

    @GetMapping
    public ResponseEntity<List<Pessoa>> obterTodasAsPessoas() {
        return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> obterUmaPessoa(@PathVariable String id){
        Optional<Pessoa> pes = servico.obterPorId(id);

        if (pes.isPresent()) {
            return new ResponseEntity<>(pes.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<PessoaResponse> criarUmaPessoa(@RequestBody PessoaRequest pessoa) {
        ModelMapper mapa = new ModelMapper();
        PessoaDTO pessoaDTO = mapa.map(pessoa, PessoaDTO.class);
        pessoaDTO = servico.criarPessoa(pessoaDTO);
        return new ResponseEntity<>(mapa.map(pessoaDTO, PessoaResponse.class) , HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id){
        servico.removerPessoa(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable String id, @RequestBody Pessoa pessoa) {
        return new ResponseEntity<>(servico.atualizarPessoa(id, pessoa), HttpStatus.OK);
    }
}
