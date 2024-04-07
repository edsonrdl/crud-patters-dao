package com.testeadmissao.testeadmissao.Infrastructure.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.testeadmissao.testeadmissao.Infrastructure.Model.ClientEntity;
import com.testeadmissao.testeadmissao.domain.Interfaces.useCases.IClientDAO;
import com.testeadmissao.testeadmissao.domain.entities.Client;


public class ClientDAOImpl implements IClientDAO {

    private final Connection connection;

    public ClientDAOImpl(Connection connection) {
        this.connection = connection;
    }

@Override
public ClientEntity save(ClientEntity clientEntity) {
    String sql = "INSERT INTO client (nome, cpf, idade) VALUES (?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, clientEntity.getName());
        statement.setString(2, clientEntity.getCpf());
        statement.setInt(3, clientEntity.getAge());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            clientEntity.setCodeClient(generatedKeys.getLong(1));
            return clientEntity;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // ou lance uma exceção se necessário
}


    @Override
    public Client findById(Long id) {
        String sql = "SELECT * FROM client WHERE code_cliente = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapToClient(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(mapToClient(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }


    @Override
public Client update(Client client) {
    String sql = "UPDATE client SET nome = ?, cpf = ?, idade = ? WHERE code_cliente = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, client.getName());
        statement.setString(2, client.getCpf());
        statement.setInt(3, client.getAge());
        statement.setLong(4, client.getId());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            return client;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // ou lance uma exceção se necessário
}


    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM client WHERE code_cliente = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Client mapToClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong("code_cliente"));
        client.setName(resultSet.getString("nome"));
        client.setCpf(resultSet.getString("cpf"));
        client.setAge(resultSet.getInt("idade"));
        return client;
    }
}
