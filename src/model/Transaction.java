package model;

public class Transaction {
    private String description;
    private double amount;
    private TransactionType type;
    private Category category;

    public Transaction(String description, double amount, TransactionType type, Category category) {
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " | " +
                category + " | " +
                description + " | " +
                amount + " kr";
    }
}
