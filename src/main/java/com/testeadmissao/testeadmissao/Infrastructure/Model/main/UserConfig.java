package br.com.giulianabezerra.springbootcleanarch.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.testeadmissao.testeadmissao.domain.Interfaces.useCases.IClientDAO;

import br.com.giulianabezerra.springbootcleanarch.application.UserInteractor.CreateUserInteractor;
import br.com.giulianabezerra.springbootcleanarch.domain.gateway.UserGateway;
import br.com.giulianabezerra.springbootcleanarch.domain.mapper.UserDTOMapper;
import br.com.giulianabezerra.springbootcleanarch.infrastructure.persistence.ClientDAOGateway;
import br.com.giulianabezerra.springbootcleanarch.infrastructure.persistence.ClientEntityMapper;
import br.com.giulianabezerra.springbootcleanarch.infrastructure.persistence.UserRepository;

@Configuration
public class UserConfig {

    @Bean
    public CreateUserInteractor createUserInteractor(IClientDAO userGateway) {
        return new CreateUserInteractor(userGateway);
    }

    @Bean
    public UserGateway userGateway(IClientDAO userRepository, ClientEntityMapper userEntityMapper) {
        return new ClientDAOGateway(userRepository, userEntityMapper);
    }

    @Bean
    public ClientEntityMapper userEntityMapper() {
        return new ClientEntityMapper();
    }

    @Bean
    public UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }
}
