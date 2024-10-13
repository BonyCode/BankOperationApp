package com.example.model;

import java.time.LocalDateTime;

/**
 * Модель банковской операции.
 * Содержит сумму, тип операции и дату/время.
 */
public class BankOperation {

    private double amount;  // Сумма операции
    private String type;    // Тип операции: пополнение или снятие
    private LocalDateTime dateTime;  // Дата и время операции

    public BankOperation(double amount, String type) {
        this.amount = amount;
        this.type = type;
        this.dateTime = LocalDateTime.now();
    }

    public BankOperation(double amount, String type, LocalDateTime dateTime) {
        this.amount = amount;
        this.type = type;
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Операция: " + type + ", Сумма: " + amount + ", Дата и время: " + dateTime;
    }
}

