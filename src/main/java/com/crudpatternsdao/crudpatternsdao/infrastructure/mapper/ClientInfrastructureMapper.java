package com.crudpatternsdao.crudpatternsdao.infrastructure.mapper;

import java.sql.SQLException;

import com.crudpatternsdao.crudpatternsdao.domain.entities.Client;
import com.crudpatternsdao.crudpatternsdao.infrastructure.Model.ClientModel;


public class ClientInfrastructureMapper {

    public static ClientModel toClientModel(Client client) throws SQLException {

        ClientModel clientModel = new ClientModel();

        if (client.getCodeClient() != null) {
            clientModel.setCodeClient(client.getCodeClient());
        }

        clientModel.setName(client.getName());
        clientModel.setCpf(client.getCpf());
        clientModel.setAge(client.getAge());
        return clientModel;
    }

    public static Client toClient(ClientModel  clientModel ) throws SQLException {

        Client client = new Client();

        client.setCodeClient(clientModel.getCodeClient());

        client.setCodeClient(clientModel.getCodeClient());
        client.setName(clientModel.getName());
        client.setCpf(clientModel.getCpf());
        client.setAge(clientModel.getAge());
        return client;
    }

}
