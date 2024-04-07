package com.testeadmissao.testeadmissao.Infrastructure.mapper;

import com.testeadmissao.testeadmissao.Infrastructure.Model.ClientEntity;
import com.testeadmissao.testeadmissao.domain.entities.Client;

public class ClientEntityMapper {
  public Client toDomainObj(ClientEntity clientEntity) {
      Client client = new Client();
      client.setName(clientEntity.getName());
      client.setCpf(clientEntity.getCpf());
      client.setAge(clientEntity.getAge());
      return client;
  }

  public ClientEntity toEntity(Client client) {
      ClientEntity clientEntity = new ClientEntity();
      clientEntity.setName(client.getName());
      clientEntity.setCpf(client.getCpf());
      clientEntity.setAge(client.getAge());
      return clientEntity;
  }
}
