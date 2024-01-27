package src;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileHandler {
    private static final String FILE_PATH = "accounts.csv";

    public static void saveAccount(Account account) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            out.println(account.getId() + "," + account.getFullName() + "," + account.getBalance() + "," + account.getCurrency());
        } catch (IOException e) {
            System.out.println("Error saving account data: " + e.getMessage());
        }
    }

    public static void updateAccount(Account account) {
        Map<String, Account> accounts = loadAccounts();
        accounts.put(account.getId(), account);
        saveAllAccounts(accounts);
    }

    public static void deleteAccount(String id) {
        Map<String, Account> accounts = loadAccounts();
        accounts.remove(id);
        saveAllAccounts(accounts);
    }

    public static Map<String, Account> loadAccounts() {
        Map<String, Account> accounts = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            boolean isFirstLine = true; // Flag to check if it's the first line (header)
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Skip the header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                String[] data = line.split(",");
                // Assuming the format is id, first name, last name, balance, currency
                String id = data[0];
                String firstName = data[1];
                String lastName = data[2];
                double balance = 0;
                
                try {
                    balance = Double.parseDouble(data[3]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid balance format for account ID " + id + ": " + data[3]);
                    continue; // Skip this account and go to the next line
                }
                
                String currency = data[4];
                Account account = new Account(firstName, lastName, balance, currency);
                accounts.put(id, account);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found " + e.getMessage());
        }
        return accounts;
    }
    
    

    private static void saveAllAccounts(Map<String, Account> accounts) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Account account : accounts.values()) {
                out.println(account.getId() + "," + account.getFullName() + "," + account.getBalance() + "," + account.getCurrency());
            }
        } catch (IOException e) {
            System.out.println("Error saving all account data: " + e.getMessage());
        }
    }
}
