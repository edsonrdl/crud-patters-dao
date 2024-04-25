package com.testeadmissao.testeadmissao.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.testeadmissao.testeadmissao.application.useCases.clientUseCases.ClientMapper;
import com.testeadmissao.testeadmissao.application.useCases.clientUseCases.ClientRequestDTO;
import com.testeadmissao.testeadmissao.application.useCases.clientUseCases.ClientResponseDTO;
import com.testeadmissao.testeadmissao.application.useCases.clientUseCases.IClientUseCase;
import com.testeadmissao.testeadmissao.domain.interfaces.useCases.IGenericDAO;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;


@RestController
@RequestMapping("/client")
public class ClientController {
  
    @Autowired
    private final IClientUseCase _IClientUseCase;

    @Autowired
    private final ClientMapper _createClientMapper;

    public ClientController(IClientUseCase clientUseCase, ClientMapper createClientMapper) {
        _IClientUseCase = clientUseCase;
      _createClientMapper = createClientMapper;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
          ClientEntity client = _createClientMapper.toEntity(clientRequestDTO);

          client = _IClientUseCase.save(client);
          
        ClientResponseDTO responseDTO = _createClientMapper.toDTO(client);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
    //     ClientEntity clientEntity = _IClientUseCase.findById(id);

    //     if (clientEntity == null) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }

    //     ClientResponseDTO responseDTO = _createClientMapper.toDTO(clientEntity);

    //     return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    // }

  
    // @GetMapping("/clients")
    // public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
    //     List<ClientEntity> clients = _IClientUseCase.findAll();
    //     if (clients.isEmpty()) {
    //         return ResponseEntity.noContent().build();
    //     }
    //     List<ClientResponseDTO> responseDTOs = clients.stream()
    //             .map(client -> {
    //                 ClientResponseDTO responseDTO = new ClientResponseDTO();
    //                 responseDTO.setCodeClient(client.getCodeClient());
    //                 responseDTO.setName(client.getName());
    //                 responseDTO.setCpf(client.getCpf());
    //                 responseDTO.setAge(client.getAge());
    //                 return responseDTO;
    //             })
    //             .collect(Collectors.toList());
    //     return ResponseEntity.ok(responseDTOs);
    // }
    
    // @PutMapping("/{id}")
    // public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id, @RequestBody ClientRequestDTO requestDTO) {
    //     ClientEntity existingClientEntity = _IClientUseCase.findById(id);
    //     if (existingClientEntity == null) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    //     existingClientEntity.setName(requestDTO.getName());
    //     existingClientEntity.setCpf(requestDTO.getCpf());
    //     existingClientEntity.setAge(requestDTO.getAge());
    //     _IClientUseCase.update(existingClientEntity);
    //     ClientResponseDTO responseDTO = _createClientMapper.toDTO(existingClientEntity); 
    //     return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    // }
    
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
    //     ClientEntity clientEntity = _IClientUseCase.findById(id);
    //     if (clientEntity == null) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    //     _clientDAO.delete(id);

    //     return new ResponseEntity<>(HttpStatus.OK);
    // }
    
}
