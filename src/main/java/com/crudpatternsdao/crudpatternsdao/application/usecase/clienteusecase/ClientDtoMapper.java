package com.crudpatternsdao.crudpatternsdao.application.usecase.clienteusecase;


import com.crudpatternsdao.crudpatternsdao.domain.entities.Client;


public class ClientDtoMapper {
    
    public ClientResponseDTO toClientDTO(Client client) {
        
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();

        clientResponseDTO.setCodeClient(client.getCodeClient());
        clientResponseDTO.setName(client.getName());
        clientResponseDTO.setCpf(client.getCpf()); 
        clientResponseDTO.setAge(client.getAge()); 
        return clientResponseDTO;
    }

    public Client toClient(ClientRequestDTO clientRequestDTO) {

        Client client = new Client();
        
        client.setName(clientRequestDTO.getName());
        client.setCpf(clientRequestDTO.getCpf());
        client.setAge(clientRequestDTO.getAge());
        return client; 
    }
}
