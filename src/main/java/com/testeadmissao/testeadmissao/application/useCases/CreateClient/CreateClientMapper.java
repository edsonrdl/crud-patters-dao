package com.testeadmissao.testeadmissao.application.useCases.createClient;


import com.testeadmissao.testeadmissao.domain.entities.Client;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

public class CreateClientMapper {
    public CreateClientResponseDTO toDTO(ClientEntity entity) {
        CreateClientResponseDTO dto = new CreateClientResponseDTO();
        dto.setName(entity.getName());
        dto.setCpf(entity.getCpf()); 
        dto.setAge(entity.getAge()); 
        return dto;
    }

    public ClientEntity toEntity(CreateClientRequestDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setAge(dto.getAge());
        return entity; 
    }
}
