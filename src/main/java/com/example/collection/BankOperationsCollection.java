package com.example.collection;

import com.example.model.BankOperation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BankOperationsCollection {

    private List<BankOperation> operations;

    public BankOperationsCollection() {
        this.operations = new ArrayList<>();
    }

    public BankOperationsCollection(Collection<? extends BankOperation> operations) {
        this.operations = new ArrayList<>(operations);
    }

    public void add(BankOperation operation) {
        operations.add(operation);
    }

    public BankOperation remove(int index) {
        return operations.remove(index);
    }

    public BankOperation get(int index) {
        return operations.get(index);
    }

    public void addAll(Collection<? extends BankOperation> operations) {
        this.operations.addAll(operations);
    }

    public void sort() {
        boolean sorted;
        for (int i = 0; i < operations.size() - 1; i++) {
            sorted = true;
            for (int j = 0; j < operations.size() - 1 - i; j++) {
                if (operations.get(j).getAmount() > operations.get(j + 1).getAmount()) {
                    BankOperation temp = operations.get(j);
                    operations.set(j, operations.get(j + 1));
                    operations.set(j + 1, temp);
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }

    /**
     * Метод для возврата всех операций в коллекции.
     */
    public List<BankOperation> getAllOperations() {
        return operations; // Возвращаем список всех операций
    }

    @Override
    public String toString() {
        return operations.toString();
    }
}


