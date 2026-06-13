package service;

import model.Category;
import model.Transaction;
import model.TransactionType;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private static final String FILE_NAME = "transactions.txt";

    public void saveTransactions(ArrayList<Transaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Transaction transaction : transactions) {
                writer.write(
                        transaction.getDescription() + ";" +
                                transaction.getAmount() + ";" +
                                transaction.getType() + ";" +
                                transaction.getCategory()
                );
                writer.newLine();
            }

            System.out.println("Transactions saved.");

        } catch (IOException e) {
            System.out.println("Error saving transactions.");
        }
    }

    public ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                String description = parts[0];
                double amount =Double.parseDouble(parts[1]);
                TransactionType type = TransactionType.valueOf(parts[2]);
                Category category = Category.valueOf(parts[3]);

                transactions.add(new Transaction( description, amount, type, category));
            }

            System.out.println("Transaction loaded.");

        } catch (FileNotFoundException e) {
            System.out.println("No saved transactions found.");
        }catch (IOException e) {
            System.out.println("Error loading transactions.");
        }

        return transactions;
    }
}
