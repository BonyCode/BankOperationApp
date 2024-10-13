package com.example.service;

import com.example.model.BankOperation;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки функциональности класса BankOperationService.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankOperationServiceTest {

    private BankOperationService service;

    @BeforeAll
    public void setup() {
        // Инициализация сервисного класса для работы с операциями.
        service = new BankOperationService();
        H2ConnectionManager.createTable();
    }

    @AfterEach
    public void cleanup() {
        // Очистка таблицы операций после каждого теста.
        try (Connection connection = H2ConnectionManager.getConnection()) {
            connection.createStatement().execute("DELETE FROM operations");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddOperation() {
        // Проверка добавления новой операции в базу данных
        BankOperation operation = new BankOperation(200.0, "пополнение");
        service.addOperation(operation);

        List<BankOperation> operations = service.getAllOperations();
        assertEquals(1, operations.size());
        assertEquals(200.0, operations.get(0).getAmount());
        assertEquals("пополнение", operations.get(0).getType());
    }

    @Test
    public void testGetAllOperations() {
        // Проверка получения всех операций
        service.addOperation(new BankOperation(100.0, "снятие"));
        service.addOperation(new BankOperation(300.0, "пополнение"));

        List<BankOperation> operations = service.getAllOperations();
        assertEquals(2, operations.size());
        assertEquals(100.0, operations.get(0).getAmount());
        assertEquals("снятие", operations.get(0).getType());
        assertEquals(300.0, operations.get(1).getAmount());
        assertEquals("пополнение", operations.get(1).getType());
    }

    @Test
    public void testRemoveOperation() {
        // Проверка удаления операции
        BankOperation operation1 = new BankOperation(150.0, "снятие");
        BankOperation operation2 = new BankOperation(250.0, "пополнение");
        service.addOperation(operation1);
        service.addOperation(operation2);

        List<BankOperation> operations = service.getAllOperations();
        assertEquals(2, operations.size());

        // Удаляем операцию с id 1 (или любую существующую операцию)
        service.removeOperation(operations.get(0).getId());  // Предположим, что у нас есть метод getId()

        operations = service.getAllOperations();
        assertEquals(1, operations.size());  // Ожидаем, что одна операция удалена
    }

    @Test
    public void testDateTimeOperation() {
        // Проверка правильности работы с датой и временем
        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 5, 12, 30);
        BankOperation operation = new BankOperation(250.0, "пополнение", dateTime);
        service.addOperation(operation);

        List<BankOperation> operations = service.getAllOperations();
        assertEquals(1, operations.size());
        assertEquals(dateTime, operations.get(0).getDateTime());
    }
}

