package ui;

import model.Category;
import model.Transaction;
import model.TransactionType;
import service.BudgetManager;
import service.FileHandler;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private BudgetManager manager = new BudgetManager();
    private FileHandler fileHandler = new FileHandler();

    public void start() {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addTransaction(TransactionType.INCOME);
                case 2 -> addTransaction(TransactionType.EXPENSE);
                case 3 -> viewTransactions();
                case 4 -> viewBalance();
                case 5 -> fileHandler.saveTransactions(manager.getTransactions());
                case 6 -> manager.setTransactions(fileHandler.loadTransactions());
                case 7 -> showExpensesByCategory();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== Personal Finance Tracker ===");
        System.out.println("1. Add income");
        System.out.println("2. Add expense");
        System.out.println("3. View transactions");
        System.out.println("4. View balance");
        System.out.println("5. Save transactions");
        System.out.println("6. Load transactions");
        System.out.println("7. Show expenses by category");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    private void addTransaction(TransactionType type) {
        System.out.println("Description: ");
        String description = scanner.nextLine();

        Category category = chooseCategory();


        System.out.println("Amount: ");
        double amount;

        while (true) {
            try {
                amount = Double.parseDouble(scanner.nextLine());

                if (amount <= 0) {
                    System.out.println("Amount must be greater than 0.");
                    continue;
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        Transaction transaction =
                new Transaction(description, amount, type, category);

        manager.addTransaction(transaction);

        System.out.println("Transaction added");
    }

    private void viewTransactions() {
        for (Transaction transaction : manager.getTransactions()) {
            System.out.println(transaction);
        }
    }

    private void viewBalance() {
        System.out.println("Balance: " + manager.getBalance());
    }

    private Category chooseCategory() {
        Category[] categories = Category.values();

        while (true) {
            System.out.println("Choose category:");

            for (int i = 0; i < categories.length; i++) {
                System.out.println((i + 1) + ". " + categories[i]);
            }

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice >= 1 && choice <= categories.length) {
                    return categories[choice - 1];
                }

                System.out.println("Invalid category.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void showExpensesByCategory() {
        System.out.println("\n=== Expenses by Category ===");

        for (Category category : Category.values()) {
            double total = manager.getExpensesByCategory(category);

            if (total > 0) {
                System.out.println(category + ": " + total + " kr");
            }
        }
    }

}
