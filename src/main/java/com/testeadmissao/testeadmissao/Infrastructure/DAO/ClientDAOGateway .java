package com.testeadmissao.testeadmissao.Infrastructure.DAO;

import java.util.List;

import com.testeadmissao.testeadmissao.Infrastructure.mapper.ClientEntityMapper;
import com.testeadmissao.testeadmissao.domain.Interfaces.useCases.IClientDAO;
import com.testeadmissao.testeadmissao.domain.entities.Client;

public class ClientDAOGateway implements IClientDAO {

    private final ClientDAOImpl clientDAOImpl;
    private final ClientEntityMapper clientEntityMapper;

    public ClientDAOGateway(ClientDAOImpl clientDAOImpl, ClientEntityMapper clientEntityMapper) {
        this.clientDAOImpl = clientDAOImpl;
        this.clientEntityMapper = clientEntityMapper;
    }

    @Override
    public Client findById(Long id) {
        return clientEntityMapper.toDomainObj(clientDAOImpl.findById(id));
    }

    @Override
    public List<Client> findAll() {
        List<ClientEntity> clientEntities = clientDAOImpl.findAll();
        return clientEntityMapper.toDomainObjList(clientEntities);
    }

    @Override
    public Client save(Client client) {
        return clientEntityMapper.toDomainObj(clientDAOImpl.save(clientEntityMapper.toEntity(client)));
    }

    @Override
    public void delete(Long id) {
        clientDAOImpl.delete(id);
    }
}
