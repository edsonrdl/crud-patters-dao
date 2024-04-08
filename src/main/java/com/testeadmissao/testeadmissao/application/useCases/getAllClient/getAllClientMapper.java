package com.testeadmissao.testeadmissao.application.useCases.getAllClient;

import com.testeadmissao.testeadmissao.domain.entities.Client;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

public class GetAllClientMapper {
    public static GetAllClientRequestDTO toDTO(Client client) {
        GetAllClientRequestDTO dto = new GetAllClientRequestDTO();
        dto.setName(client.getName());
        dto.setCpf(client.getCpf()); 
        dto.setAge(client.getAge()); 
        return dto;
    }

    public static ClientEntity toEntity(GetAllClientRequestDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setAge(dto.getAge());
        return entity; 
    }
}
