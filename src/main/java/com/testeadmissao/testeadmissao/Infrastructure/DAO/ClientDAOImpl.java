package com.testeadmissao.testeadmissao.infrastructure.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.testeadmissao.testeadmissao.domain.entities.Client;
import com.testeadmissao.testeadmissao.domain.interfaces.useCases.IGenericDAO;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

public class ClientDAOImpl implements IGenericDAO<ClientEntity, Long> {
    private static final String URL = "jdbc:h2:~/test";
    private static final String USUARIO = "SA";
    private static final String SENHA = "senha";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    @Override
    public ClientEntity findById(Long id) {
        ClientEntity cliente = null;
        String sql = "SELECT * FROM client WHERE code_client=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClientEntity();
                    cliente.setCodeClient(rs.getLong("code_client"));
                    cliente.setName(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setAge(rs.getInt("idade"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
    

    @Override
    public List<ClientEntity> findAll() {
        List<ClientEntity> clientes = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ClientEntity cliente = new ClientEntity(
                    rs.getLong("code_client"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getInt("idade")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public ClientEntity save(ClientEntity entity) {
        String sql = "INSERT INTO client (nome, cpf, idade) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getCpf());
            ps.setInt(3, entity.getAge());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating client failed, no rows affected.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setCodeClient(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating client failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving client entity", e);
        }
        return entity;
    }
    
    
@Override
public void update(ClientEntity entity) {
    String sql = "UPDATE client SET nome=?, cpf=?, idade=? WHERE code_client=?";
    try (Connection connection = getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getCpf());
        ps.setInt(3, entity.getAge());
        ps.setLong(4, entity.getCodeClient());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error updating client entity", e);
    }
}

@Override
public void delete(Long id) {
    String sql = "DELETE FROM client WHERE code_client=?";
    try (Connection connection = getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setLong(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error deleting client entity", e);
    }
}
}
