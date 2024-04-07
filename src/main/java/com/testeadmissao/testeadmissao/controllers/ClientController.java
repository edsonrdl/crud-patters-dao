package com.testeadmissao.testeadmissao.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.testeadmissao.testeadmissao.application.useCases.createClient.CreateClientMapper;
import com.testeadmissao.testeadmissao.application.useCases.createClient.CreateClientRequestDTO;
import com.testeadmissao.testeadmissao.application.useCases.createClient.CreateClientResponseDTO;
import com.testeadmissao.testeadmissao.domain.interfaces.useCases.IGenericDAO;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;


@RestController
@RequestMapping("/client")
public class ClientController {
  

    private final IGenericDAO<ClientEntity, Long> _clientDAO;

    private final CreateClientMapper _createClientMapper;

    public ClientController(IGenericDAO<ClientEntity, Long> clientDAO, CreateClientMapper createClientMapper) {
      _clientDAO = clientDAO;
      _createClientMapper = createClientMapper;
    }

    @PostMapping
    public ResponseEntity<CreateClientResponseDTO> createClient(@RequestBody CreateClientRequestDTO clientRequestDTO) {
    // Mapeamento de CreateClientRequestDTO para ClientEntity
    ClientEntity clientEntity = _createClientMapper.toEntity(clientRequestDTO);

    // Salvando o cliente no banco de dados
    clientEntity = _clientDAO.save(clientEntity);

    // Mapeamento de ClientEntity para CreateClientResponseDTO
    CreateClientResponseDTO responseDTO = _createClientMapper.toDTO(clientEntity);

    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
}

    // @GetMapping("/{id}")
    // public ResponseEntity<CreateClientResponseDTO> getClientById(@PathVariable Long id) {
    //     // Busca o cliente pelo ID
    //     ClientEntity clientEntity = _clientDAO.findById(id);

    //     // Se o cliente não for encontrado, retorna status 404 Not Found
    //     if (clientEntity == null) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }

    //     // Mapeamento de ClientEntity para CreateClientResponseDTO
    //     CreateClientResponseDTO responseDTO = _createClientMapper.toCreateClientResponseDTO(clientEntity);

    //     return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    // }

    // @GetMapping("/all")
    // public ResponseEntity<List<CreateClientResponseDTO>> getAllClients() {
    //     // Busca todos os clientes
    //     List<ClientEntity> clients = _clientDAO.findAll();

    //     // Se não houverem clientes, retorna uma lista vazia
    //     if (clients.isEmpty()) {
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     }

    //     // Mapeamento de ClientEntity para CreateClientResponseDTO
    //     List<CreateClientResponseDTO> responseDTOs = _createClientMapper.toCreateClientResponseDTOList(clients);

    //     return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Void> updateClient(@PathVariable Long id, @RequestBody ClientRequestDTO clientRequestDTO) {
    //     // Busca o cliente pelo ID
    //     ClientEntity existingClientEntity = _clientDAO.findById(id);

    //     // Se o cliente não for encontrado, retorna status 404 Not Found
    //     if (existingClientEntity == null) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }

    //     // Atualiza os dados do cliente
    //     existingClientEntity.setName(clientRequestDTO.getName());
    //     existingClientEntity.setCpf(clientRequestDTO.getCpf());
    //     existingClientEntity.setAge(clientRequestDTO.getAge());

    //     // Atualiza o cliente no banco de dados
    //     _clientDAO.update(existingClientEntity);

    //     return new ResponseEntity<>(HttpStatus.OK);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
    //     // Busca o cliente pelo ID
    //     ClientEntity clientEntity = _clientDAO.findById(id);

    //     // Se o cliente não for encontrado, retorna status 404 Not Found
    //     if (clientEntity == null) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }

    //     // Deleta o cliente do banco de dados
    //     clientDAO.delete(id);

    //     return new ResponseEntity<>(HttpStatus.OK);
    // }
}
