package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CidadeInputDissasembler;
import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDissasembler cidadeInputDissasembler;

    @Autowired
    private CadastroCidadeService cadastroCidade;
    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public List<CidadeModel> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();
        return cidadeModelAssembler.toCollectionModel(todasCidades);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@Valid @RequestBody CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDissasembler.toDomainObject(cidadeInput);
            cidade = cadastroCidade.salvar(cidade);
            return cidadeModelAssembler.toModel(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@Valid @PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
            cidadeInputDissasembler.copyToDomainObject(cidadeInput, cidadeAtual);
            cidadeAtual = cadastroCidade.salvar(cidadeAtual);
            return cidadeModelAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);
    }
}
