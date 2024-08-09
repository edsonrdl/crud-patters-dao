package com.crudpatternsdao.crudpatternsdao.infrastructure.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import com.crudpatternsdao.crudpatternsdao.infrastructure.Model.ClientModel;


@Primary
@Repository
public class ClientDAOImpl implements IClientDAO {
    
    private static final String URL = "jdbc:h2:~/test";
    private static final String USUARIO = "SA";
    private static final String SENHA = "senha";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    @Override
    public ClientModel save(ClientModel entity) {
        String sql = "INSERT INTO client (nome, cpf, idade) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getCpf());
            ps.setInt(3, entity.getAge());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha na criação do cliente");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setCodeClient(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Falha na criação do cliente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error ao salvar  cliente", e);
        }
        return entity;
    }

    @Override
    public ClientModel findById(Long id) {
        ClientModel cliente = null;
        String sql = "SELECT * FROM client WHERE code_client=?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClientModel();
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
    public List<ClientModel> findAll() {
        
        List<ClientModel> clientes = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ClientModel cliente = new ClientModel(
                        rs.getLong("code_client"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

   

    @Override
    public void update(ClientModel entity) {
        String sql = "UPDATE client SET nome=?, cpf=?, idade=? WHERE code_client=?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getCpf());
            ps.setInt(3, entity.getAge());
            ps.setLong(4, entity.getCodeClient());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Falha ao atualizar dados do Cliente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar a entidade cliente", e);
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
            throw new RuntimeException("Error deletar cliente", e);
        }
    }
}
