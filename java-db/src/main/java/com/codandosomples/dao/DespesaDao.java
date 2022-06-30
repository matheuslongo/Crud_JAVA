package com.codandosomples.dao;

import com.codandosomples.infra.ConectionFactory;
import com.codandosomples.model.Categoria;
import com.codandosomples.model.Despesa;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/*
@author Matheus Macedo Longo
 */
public class DespesaDao implements IDespesaDao {
    @Override
    public Despesa save(Despesa despesa) {
     try (Connection connection = ConectionFactory.getConnection()) {
         String sql = ("INSERT INTO Despesas (descricao, valor, date, categoriaa) VALUES(?, ?, ?, ?)");
         PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         preparedStatement.setString(1, despesa.getDescricao());
         preparedStatement.setDouble(2, despesa.getValor());
         preparedStatement.setDate(3, java.sql.Date.valueOf(despesa.getData()));
         preparedStatement.setString(4,despesa.getCategoria().toString());
         preparedStatement.executeUpdate();
         ResultSet resultSet = preparedStatement.getGeneratedKeys();
         resultSet.next();
         Long generatedId = resultSet.getLong("id");
         despesa.setId(generatedId);
     } catch (SQLException ex) {
         throw new RuntimeException(ex);
     }
        return despesa;
    }
    @Override
    public Despesa update(Despesa despesa){
        try (Connection connection = ConectionFactory.getConnection()) {
            String sql = ("UPDATE Despesas SET descricao = ?, valor= ?, date = ?, categoria= ? WHERE id = ?;");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, despesa.getDescricao());
            preparedStatement.setDouble(2, despesa.getValor());
            preparedStatement.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            preparedStatement.setString(4,despesa.getCategoria().toString());
            preparedStatement.setLong(5,despesa.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return despesa;
    }
    @Override
    public void delete(Long id) {
        try (Connection connection = ConectionFactory.getConnection()) {
            String sql = ("DELETE FROM Despesas WHERE id = ?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Despesa> findAll() {
        String sql = "SELECT id, descricao, date, valor, categoria FROM Despesas";
        List<Despesa> despesas;
        try (Connection connection = ConectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            despesas = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                LocalDate date = rs.getDate("date").toLocalDate();
                double valor = rs.getDouble("valor");
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
                Despesa despesa = new Despesa(id, descricao, date, valor, categoria);
                despesas.add(despesa);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return despesas;
    }
    @Override
    public Optional<Despesa> findById(Long id) {
        String sql = "SELECT id, descricao, date, valor, categoria FROM Despesas WHERE id = ?";
        Despesa despesa = null;
        try (Connection connection = ConectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long pKey = rs.getLong("id");
                String descricao = rs.getString("descricao");
                LocalDate date = rs.getDate("date").toLocalDate();
                double valor = rs.getDouble("valor");
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
                despesa = new Despesa(pKey, descricao, date, valor, categoria);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return Optional.ofNullable(despesa);
    }
    @Override
    public List<Despesa> findByCategoria(Categoria categoria) {
        String sql = "SELECT id, descricao, date, valor, categoria FROM Despesas WHERE categoria = ?";
        List<Despesa> despesas;
        try (Connection connection = ConectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,categoria.toString());
            despesas = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                LocalDate date = rs.getDate("date").toLocalDate();
                double valor = rs.getDouble("valor");
                Categoria cat = Categoria.valueOf(rs.getString("categoria"));
                Despesa despesa = new Despesa(id, descricao, date, valor, cat);
                despesas.add(despesa);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return despesas;
    }
}
