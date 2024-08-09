package com.crudpatternsdao.crudpatternsdao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crudpatternsdao.crudpatternsdao.application.dto.ClientRequestDTO;
import com.crudpatternsdao.crudpatternsdao.application.dto.ClientResponseDTO;
import com.crudpatternsdao.crudpatternsdao.application.usecase.clienteusecase.IClientUseCase;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final IClientUseCase clientUseCase;

    @Autowired
    public ClientController(IClientUseCase clientUseCase) {
        this.clientUseCase = clientUseCase;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO responseDTO = this.clientUseCase.save(clientRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
        try {
            ClientResponseDTO responseDTO = this.clientUseCase.findById(id);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        List<ClientResponseDTO> responseDTOs = this.clientUseCase.findAll();
        if (responseDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id, @RequestBody ClientRequestDTO requestDTO) {
        try {
            ClientResponseDTO responseDTO = this.clientUseCase.update(requestDTO, id);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            this.clientUseCase.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
