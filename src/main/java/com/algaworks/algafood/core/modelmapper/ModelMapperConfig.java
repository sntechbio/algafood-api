package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.model.RestauranteDtoOutput;
import com.algaworks.algafood.api.model.input.RestauranteDtoInput;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Restaurante.class, RestauranteDtoOutput.class)
                .addMapping(Restaurante::getTaxaFrete, RestauranteDtoOutput::setPrecoFrete);

        return new ModelMapper();
    }
}
