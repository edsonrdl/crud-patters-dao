package com.testeadmissao.testeadmissao.application.useCases.CreateClient;

import com.testeadmissao.testeadmissao.Infrastructure.Model.ClientEntity;
import com.testeadmissao.testeadmissao.domain.entities.Client;

public class CreateClientMapper {
    public static CreateClientRequestDTO toDTO(Client client) {
        CreateClientRequestDTO dto = new CreateClientRequestDTO();
        dto.setName(client.getName());
        dto.setCpf(client.getCpf()); 
        dto.setAge(client.getAge()); 
        return dto;
    }

    public static Client toEntity(CreateClientRequestDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setAge(dto.getAge());
        return entity; 
    }
}
