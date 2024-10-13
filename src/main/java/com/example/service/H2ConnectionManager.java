package com.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для управления подключением к базе данных H2.
 */
public class H2ConnectionManager {

    private static final String DB_URL = "jdbc:h2:./database/bank_operations";  // Путь к базе данных

    /**
     * Получение подключения к базе данных H2.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, "sa", "");  // Подключение без пароля
    }

    /**
     * Создание таблицы операций, если она не существует.
     */
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS operations (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "amount DOUBLE," +
                     "type VARCHAR(255)," +
                     "date_time TIMESTAMP)";
        try (Connection connection = getConnection()) {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

