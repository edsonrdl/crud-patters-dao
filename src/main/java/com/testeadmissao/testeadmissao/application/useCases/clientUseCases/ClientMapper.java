package com.testeadmissao.testeadmissao.application.useCases.clientUseCases;


import com.testeadmissao.testeadmissao.domain.entities.Client;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

public class ClientMapper {
    public ClientResponseDTO toDTO(ClientEntity entity) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setCodeClient(entity.getCodeClient());
        dto.setName(entity.getName());
        dto.setCpf(entity.getCpf()); 
        dto.setAge(entity.getAge()); 
        return dto;
    }

    public ClientEntity toEntity(ClientRequestDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setAge(dto.getAge());
        return entity; 
    }
}
