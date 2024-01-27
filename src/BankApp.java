package src;
import java.util.Scanner;
import java.util.Map;


public class BankApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Account> accounts = FileHandler.loadAccounts();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to the BankApp. Please choose an option:");
            System.out.println("1. Create Account\n2. Delete Account\n3. Deposit\n4. Withdraw\n5. Check Balance\n6. Convert Currency\n7. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deleteAccount();
                    break;
                case 3:
                    handleDeposit();
                    break;
                case 4:
                    handleWithdraw();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    convertCurrency();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void createAccount() {
        System.out.println("Enter First Name:");
        String firstName = scanner.next();
        System.out.println("Enter Last Name:");
        String lastName = scanner.next();
        System.out.println("Enter Opening Deposit Amount:");
        double deposit = scanner.nextDouble();
        System.out.println("Enter Currency:");
        String currency = scanner.next();

        Account newAccount = new Account(firstName, lastName, deposit, currency);
        accounts.put(newAccount.getId(), newAccount);
        System.out.println("Account Created Successfully. Account ID: " + newAccount.getId());
    }

    private static void deleteAccount() {
        System.out.println("Enter Account ID:");
        String id = scanner.next();
        if (accounts.containsKey(id)) {
            accounts.remove(id);
            FileHandler.deleteAccount(id);
            System.out.println("Account Deleted Successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void handleDeposit() {
        System.out.println("Enter Account ID:");
        String id = scanner.next();
        if (accounts.containsKey(id)) {
            System.out.println("Enter Deposit Amount:");
            double amount = scanner.nextDouble();
            System.out.println("Enter Currency:");
            String currency = scanner.next();

            Account account = accounts.get(id);
            account.deposit(amount, currency);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void handleWithdraw() {
        // Similar to handleDeposit, but for withdrawals
    }

    private static void checkBalance() {
        System.out.println("Enter Account ID:");
        String id = scanner.next();
        if (accounts.containsKey(id)) {
            Account account = accounts.get(id);
            System.out.println("Account Balance: " + account.getBalance() + " " + account.getCurrency());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void convertCurrency() {
        System.out.println("Currency conversion functionality is not implemented yet.");
        // Implement conversion logic using CurrencyConverter class
    }
}
