// Pacote: com.crudpatternsdao.crudpatternsdao.application.usecase.clienteusecase

package com.crudpatternsdao.crudpatternsdao.application.usecase.clienteusecase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudpatternsdao.crudpatternsdao.infrastructure.Model.ClientModel;
import com.crudpatternsdao.crudpatternsdao.infrastructure.dao.IClientDAO;
import com.crudpatternsdao.crudpatternsdao.infrastructure.mapper.ClientInfrastructureMapper;

import ch.qos.logback.core.net.server.Client;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientUseCase implements IClientUseCase {

    private final IClientDAO clientDAO;
    
    private final ClientDtoMapper clientDtoMapper;

    private final ClientInfrastructureMapper clientInfrastructureMapper;

    @Autowired
    public ClientUseCase(IClientDAO clientDAO,ClientDtoMapper clientDtoMapper, ClientInfrastructureMapper clientInfrastructureMapper) {
        this.clientDAO = clientDAO;
        this.clientDtoMapper = clientDtoMapper;
        this.clientDtoMapper = clientDtoMapper;
        this.clientInfrastructureMapper=clientInfrastructureMapper;
    }

    @Override
    public ClientResponseDTO save(ClientRequestDTO clientRequestDTO) {

        Client client =this.clientDtoMapper.toClient(clientRequestDTO);
        ClientModel clientModel=this.clientInfrastructureMapper.toClientModel(client);
        clientDAO.save(client);
        Client savedClient = clientEntityMapper.toDomain(savedEntity);
        return ClientDtoMapper.toDto(savedClient);
    }

    @Override
    public ClientResponseDTO findById(Long id) {
        ClientModel entity = clientDAO.findById(id);
        if (entity == null) {
            throw new RuntimeException("Client not found");
        }
        Client client = clientEntityMapper.toDomain(entity);
        return ClientDtoMapper.toDto(client);
    }

    @Override
    public List<ClientResponseDTO> findAll() {
        List<ClientModel> entities = clientDAO.findAll();
        return entities.stream()
                .map(clientEntityMapper::toDomain)
                .map(ClientDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponseDTO update(ClientRequestDTO clientRequestDTO, Long id) {

        ClientModel existingEntity = clientDAO.findById(id);

        if (existingEntity == null) {
            throw new RuntimeException("Client not found");
        }
        Client client = ClientDtoMapper.toDomain(clientRequestDTO);
        ClientModel entity = clientEntityMapper.toEntity(client);
        clientDAO.update(entity);
        Client updatedClient = clientEntityMapper.toDomain(entity);
        return ClientDtoMapper.toDto(updatedClient);
    }

    @Override
    public void delete(Long id) {
        ClientModel existingEntity = clientDAO.findById(id);
        if (existingEntity == null) {
            throw new RuntimeException("Client not found");
        }
        clientDAO.delete(id);
    }
}
