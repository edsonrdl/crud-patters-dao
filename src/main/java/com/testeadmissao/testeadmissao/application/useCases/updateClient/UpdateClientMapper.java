package com.testeadmissao.testeadmissao.application.useCases.updateClient;

import com.testeadmissao.testeadmissao.domain.entities.Client;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

public class UpdateClientMapper {
    public static UpdateClientRequestDTO toDTO(Client client) {
        UpdateClientRequestDTO dto = new UpdateClientRequestDTO();
        dto.setName(client.getName());
        dto.setCpf(client.getCpf()); 
        dto.setAge(client.getAge()); 
        return dto;
    }

    public static ClientEntity toEntity(UpdateClientRequestDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setAge(dto.getAge());
        return entity; 
    }
}
