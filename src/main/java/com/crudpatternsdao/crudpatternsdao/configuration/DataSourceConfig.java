package com.crudpatternsdao.crudpatternsdao.configuration;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.crudpatternsdao.crudpatternsdao.application.usecase.clienteusecase.ClientMapper;
import com.crudpatternsdao.crudpatternsdao.application.usecase.clienteusecase.IClientUseCase;
import com.crudpatternsdao.crudpatternsdao.controllers.ClientController;
import com.crudpatternsdao.crudpatternsdao.domain.interfaces.useCases.IGenericDAO;
import com.crudpatternsdao.crudpatternsdao.infrastructure.dao.ClientDAOImpl;
import com.crudpatternsdao.crudpatternsdao.infrastructure.mapper.ClientEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataSourceConfig {
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb"); 
        dataSource.setUsername("SA");
        dataSource.setPassword("senha");
        return dataSource;
    }

    @Bean
    public ClientMapper createClientMapper() {
        return new ClientMapper();
    }

 
    @Bean
    public ClientEntityMapper clientEntityMapper() {
        return new ClientEntityMapper();
    }

    @Bean
    public ClientController clientController(IClientUseCase iClientUseCase, ClientMapper createClientMapper) {
        return new ClientController(iClientUseCase, createClientMapper);
    }
    
}
