package com.algaworks.algafood.api.assembler;

import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;
import com.algaworks.algafood.Groups;
import org.modelmapper.ModelMapper;
import com.algaworks.algafood.api.model.input.RestauranteDtoInput;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteDtoInput restauranteDtoInput) {
        return modelMapper.map(restauranteDtoInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteDtoInput restauranteDtoInput, Restaurante restaurante) {
        restaurante.setCozinha(new Cozinha());

        modelMapper.map(restauranteDtoInput, restaurante);
    }

}
