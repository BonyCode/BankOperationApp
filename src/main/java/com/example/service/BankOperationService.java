package com.example.service;

import com.example.model.BankOperation;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервисный класс для работы с банковскими операциями в базе данных H2.
 */
public class BankOperationService {

    /**
     * Добавление новой операции в базу данных.
     */
    public void addOperation(BankOperation operation) {
        String sql = "INSERT INTO operations (amount, type, date_time) VALUES (?, ?, ?)";
        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, operation.getAmount());
            statement.setString(2, operation.getType());
            statement.setTimestamp(3, Timestamp.valueOf(operation.getDateTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение всех операций из базы данных.
     */
    public List<BankOperation> getAllOperations() {
        List<BankOperation> operations = new ArrayList<>();
        String sql = "SELECT amount, type, date_time FROM operations";
        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                String type = resultSet.getString("type");
                LocalDateTime dateTime = resultSet.getTimestamp("date_time").toLocalDateTime();
                operations.add(new BankOperation(amount, type, dateTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operations;
    }

    /**
     * Удаление операции по идентификатору.
     */
    public void removeOperation(int id) {
        String sql = "DELETE FROM operations WHERE id = ?";
        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

