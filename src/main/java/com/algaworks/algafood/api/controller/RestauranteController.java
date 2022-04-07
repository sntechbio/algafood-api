package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {
        return cadastroRestaurante.buscarOuFalhar(restauranteId);
    }

    @PostMapping
    public Restaurante adicionar(@RequestBody Restaurante restaurante) {
        try {
            return cadastroRestaurante.salvar(restaurante);
        } catch (CozinhaNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco",
                "dataCadastro", "produtos");

        try {
            return cadastroRestaurante.salvar(restaurante);
        } catch (CozinhaNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public Restaurante atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
        merge(campos, restauranteAtual);

        return atualizar(restauranteId, restauranteAtual);
        }

        private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

                Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
                System.out.println(restauranteOrigem);

                camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                    Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                    field.setAccessible(true);

                    Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                    System.out.println(nomePropriedade + " = " + valorPropriedade);
                    ReflectionUtils.setField(field, restauranteDestino, valorPropriedade);
                });
            } catch (IllegalArgumentException e) {
                Throwable rootCause = ExceptionUtils.getRootCause(e);
            }

        }

    }