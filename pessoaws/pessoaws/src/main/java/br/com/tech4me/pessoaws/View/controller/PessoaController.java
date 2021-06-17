package br.com.tech4me.pessoaws.View.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import br.com.tech4me.pessoaws.service.PessoaService;


@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    @Autowired
    PessoaService servico;

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> obterTodasAsPessoas() {
        ModelMapper mapa = new ModelMapper();
        List<PessoaDTO> pessoaDTO = servico.obterTodos();
        List<PessoaResponse> pesResponse = pessoaDTO.stream()
        .map(pes -> mapa.map(pes, PessoaResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(pesResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaResponse> obterUmaPessoa(@PathVariable String id){
        Optional<PessoaDTO> pes = servico.obterPorId(id);

        if (pes.isPresent()) {
            return new ResponseEntity<>(new ModelMapper().map(pes.get(), PessoaResponse.class), HttpStatus.FOUND);
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
    public ResponseEntity<PessoaResponse> atualizarPessoa(@PathVariable String id, @RequestBody PessoaRequest pessoa) {
        ModelMapper mapa = new ModelMapper();
        PessoaDTO pessoaDTO = mapa.map(pessoa, PessoaDTO.class);
        pessoaDTO = servico.atualizarPessoa(id, pessoaDTO);
        return new ResponseEntity<>(mapa.map(pessoaDTO, PessoaResponse.class) , HttpStatus.OK);

    }
}
