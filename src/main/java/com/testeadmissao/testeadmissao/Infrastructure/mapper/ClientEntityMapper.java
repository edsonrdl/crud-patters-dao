package com.testeadmissao.testeadmissao.infrastructure.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.testeadmissao.testeadmissao.domain.entities.Client;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

public class ClientEntityMapper {

    public static ClientEntity toClientEntity(ResultSet resultSet) throws SQLException {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setCodeClient(resultSet.getLong("code_client"));
        clientEntity.setName(resultSet.getString("nome"));
        clientEntity.setCpf(resultSet.getString("cpf"));
        clientEntity.setAge(resultSet.getInt("idade"));
        return clientEntity;
    }
    public static Client toClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setName(resultSet.getString("nome"));
        client.setCpf(resultSet.getString("cpf"));
        client.setAge(resultSet.getInt("idade"));
        return client;
    }

}
