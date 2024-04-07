package com.testeadmissao.testeadmissao.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.testeadmissao.testeadmissao.application.useCases.createClient.CreateClientMapper;
import com.testeadmissao.testeadmissao.controllers.ClientController;
import com.testeadmissao.testeadmissao.domain.interfaces.useCases.IGenericDAO;
import com.testeadmissao.testeadmissao.infrastructure.dao.ClientDAOImpl; // Importe a classe ClientDAOImpl aqui
import com.testeadmissao.testeadmissao.infrastructure.mapper.ClientEntityMapper;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

@Configuration
public class DataSourceConfig {
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/test");
        dataSource.setUsername("SA");
        dataSource.setPassword("senha");
        return dataSource;
    }

    @Bean
    public CreateClientMapper createClientMapper() {
        return new CreateClientMapper();
    }
    
    @Bean
    public IGenericDAO<ClientEntity, Long> clientDAO() {
        return new ClientDAOImpl(); // Crie uma instância de ClientDAOImpl aqui
    }

 
    @Bean
    public ClientEntityMapper clientEntityMapper() {
        return new ClientEntityMapper();
    }

    @Bean
    public ClientController clientController(IGenericDAO<ClientEntity, Long> clientDAO, CreateClientMapper createClientMapper) {
        return new ClientController(clientDAO, createClientMapper);
    }
}
