package com.crudpatternsdao.crudpatternsdao.application.usecase.clienteusecase;

import java.util.List;

import com.crudpatternsdao.crudpatternsdao.domain.entities.Client;

public interface IClientUseCase {
    Client save(Client client);
    Client findAll();
    List<Client> findById(Long id);
    Client update(Client client);
    Client delete(Long id);
}
