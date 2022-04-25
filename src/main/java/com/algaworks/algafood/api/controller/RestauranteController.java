package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.CozinhaDtoOutput;
import com.algaworks.algafood.api.model.RestauranteDtoOutput;
import com.algaworks.algafood.api.model.input.RestauranteDtoInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @GetMapping
    public List<RestauranteDtoOutput> listar() {
        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteDtoOutput buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return restauranteModelAssembler.toModel(restaurante);
    }

    @PostMapping
    public RestauranteDtoOutput adicionar(@Valid @RequestBody RestauranteDtoInput restauranteDtoInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteDtoInput);
            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
        } catch (CozinhaNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteDtoOutput atualizar(@PathVariable Long restauranteId,
                                          @RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
        try {
            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
            restauranteInputDisassembler.copyToDomainObject(restauranteDtoInput, restauranteAtual);

            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
