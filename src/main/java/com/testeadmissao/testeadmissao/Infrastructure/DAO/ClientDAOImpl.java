package com.testeadmissao.testeadmissao.infrastructure.dao;

import com.testeadmissao.testeadmissao.domain.interfaces.useCases.IGenericDAO;
import com.testeadmissao.testeadmissao.infrastructure.mapper.ClientEntityMapper;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ClientDAOImpl implements IGenericDAO<ClientEntity, Long> {

    private final ClientEntityMapper clientEntityMapper;
    private final DataSource dataSource;

    public ClientDAOImpl(DataSource dataSource, ClientEntityMapper clientEntityMapper) {
        this.dataSource = dataSource;
        this.clientEntityMapper = clientEntityMapper;
    }

    public ClientEntity save(ClientEntity clientEntity) {
        String sql = "INSERT INTO client (nome, cpf, idade) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
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
        return null;
    }

    public ClientEntity findById(Long id) {
        String sql = "SELECT * FROM client WHERE code_cliente = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return clientEntityMapper.toClientEntity(resultSet); // Corrigido para toClientEntity
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ClientEntity> findAll() {
        List<ClientEntity> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(clientEntityMapper.toClientEntity(resultSet)); // Corrigido para toClientEntity
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void update(ClientEntity clientEntity) {
        String sql = "UPDATE client SET nome = ?, cpf = ?, idade = ? WHERE code_cliente = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, clientEntity.getName());
            statement.setString(2, clientEntity.getCpf());
            statement.setInt(3, clientEntity.getAge());
            statement.setLong(4, clientEntity.getCodeClient());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated <= 0) {
                throw new SQLException("Failed to update client");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM client WHERE code_cliente = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
