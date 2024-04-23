package com.es.iesmz.FitGoal.helper;

import com.es.iesmz.FitGoal.DTO.Session.DtoSession;
import com.es.iesmz.FitGoal.domain.Session;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.typeMap(DtoSession.class, Session.class).addMappings(mapper -> {
            mapper.skip(Session::setId);
        });
        return modelMapper;
    }
}