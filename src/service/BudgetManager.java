package service;

import model.Category;
import model.Transaction;
import model.TransactionType;

import java.util.ArrayList;

public class BudgetManager {

    private ArrayList<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.INCOME) {
                balance += transaction.getAmount();
            } else {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getExpensesByCategory(Category category) {
        double total = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE &&
                    transaction.getCategory() == category) {
                total += transaction.getAmount();
            }
        }

        return total;
    }

}
