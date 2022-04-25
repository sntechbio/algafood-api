package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.CozinhaDtoOutput;
import com.algaworks.algafood.api.model.RestauranteDtoOutput;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public List<RestauranteDtoOutput> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
    }

    public RestauranteDtoOutput toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDtoOutput.class);
    }

}
