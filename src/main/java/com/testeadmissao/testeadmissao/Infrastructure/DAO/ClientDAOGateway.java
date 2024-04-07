package com.testeadmissao.testeadmissao.infrastructure.dao;

import java.util.List;

import com.testeadmissao.testeadmissao.domain.interfaces.useCases.IGenericDAO;
import com.testeadmissao.testeadmissao.infrastructure.mapper.ClientEntityMapper;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

public class ClientDAOGateway implements IGenericDAO<ClientEntity, Long> {

    private final ClientDAOImpl clientDAOImpl;
    

    public ClientDAOGateway(ClientDAOImpl clientDAOImpl, ClientEntityMapper clientEntityMapper) {
        this.clientDAOImpl = clientDAOImpl;
    }

    @Override
    public ClientEntity findById(Long id) {
        ClientEntity clientEntity = clientDAOImpl.findById(id);
        if (clientEntity != null) {
            return clientEntity;
        }
        return null;
    }

    @Override
    public List<ClientEntity> findAll() {
        return clientDAOImpl.findAll();
    }

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        return clientDAOImpl.save(clientEntity);
    }

    @Override
    public void update(ClientEntity clientEntity) {
        clientDAOImpl.update(clientEntity);
    }

    @Override
    public void delete(Long id) {
        clientDAOImpl.delete(id);
    }
}
