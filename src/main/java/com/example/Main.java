package com.example;

import com.example.collection.BankOperationsCollection;
import com.example.model.BankOperation;
import com.example.service.BankOperationService;
import com.example.service.H2ConnectionManager;

import java.util.List;
import java.util.Scanner;

/**
 * Главный класс программы с консольным интерфейсом.
 */
public class Main {

    public static void main(String[] args) {
        // Создание таблицы операций в базе данных.
        H2ConnectionManager.createTable();

        BankOperationService service = new BankOperationService();
        BankOperationsCollection collection = new BankOperationsCollection(service.getAllOperations());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Вывод меню
            System.out.println("Меню:");
            System.out.println("1. Добавить операцию");
            System.out.println("2. Удалить операцию");
            System.out.println("3. Показать все операции");
            System.out.println("4. Сортировать операции по сумме");
            System.out.println("5. Выйти");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Пропуск новой строки

            switch (choice) {
                case 1:
                    System.out.print("Введите сумму: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  // Пропуск новой строки

                    System.out.print("Введите тип операции (пополнение/снятие): ");
                    String type = scanner.nextLine();

                    BankOperation newOperation = new BankOperation(amount, type);
                    service.addOperation(newOperation);
                    collection.add(newOperation);
                    break;

                case 2:
                    List<BankOperation> operations = collection.getAllOperations();
                    for (int i = 0; i < operations.size(); i++) {
                        System.out.println((i + 1) + ". " + operations.get(i));
                    }
                    System.out.print("Введите номер операции для удаления: ");
                    int index = scanner.nextInt();
                    service.removeOperation(index);
                    collection.remove(index - 1);
                    break;

                case 3:
                    System.out.println("Список операций:");
                    System.out.println(collection);
                    break;

                case 4:
                    collection.sort();
                    System.out.println("Операции отсортированы по сумме.");
                    break;

                case 5:
                    System.out.println("Выход.");
                    return;

                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }
}

