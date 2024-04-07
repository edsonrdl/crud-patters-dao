package com.testeadmissao.testeadmissao.domain.Interfaces.useCases;

import java.util.List;

import com.testeadmissao.testeadmissao.domain.entities.Client;

public interface IClientDAO {
    Client findById(Long id);
    List<Client> findAll();
    Client save(Client client);
    Client update(Client client);
    Client delete(Long id);
}
