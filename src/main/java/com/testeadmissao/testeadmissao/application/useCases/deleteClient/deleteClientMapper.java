package com.testeadmissao.testeadmissao.application.useCases.deleteClient;

import com.testeadmissao.testeadmissao.domain.entities.Client;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

public class deleteClientMapper {
    public static deleteClientRequestDTO toDTO(Client client) {
        deleteClientRequestDTO dto = new deleteClientRequestDTO();
        dto.setName(client.getName());
        dto.setCpf(client.getCpf()); 
        dto.setAge(client.getAge()); 
        return dto;
    }

    public static ClientEntity toEntity(deleteClientRequestDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setAge(dto.getAge());
        return entity; 
    }
}
