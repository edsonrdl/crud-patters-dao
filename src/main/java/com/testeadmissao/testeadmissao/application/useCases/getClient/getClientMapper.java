package com.testeadmissao.testeadmissao.application.useCases.getClient;

import com.testeadmissao.testeadmissao.domain.entities.Client;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

public class GetClientMapper {
    public static GetClientRequestDTO toDTO(Client client) {
        GetClientRequestDTO dto = new GetClientRequestDTO();
        dto.setName(client.getName());
        dto.setCpf(client.getCpf()); 
        dto.setAge(client.getAge()); 
        return dto;
    }

    public static ClientEntity toEntity(GetClientRequestDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setAge(dto.getAge());
        return entity; 
    }
}
