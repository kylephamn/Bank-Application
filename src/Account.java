package src;
public class Account {
    private String id;
    private String firstName;
    private String lastName;
    private double balance;
    private String currency;

    public Account(String firstName, String lastName, double openingDeposit, String currency) {
        this.id = Utils.generateId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = openingDeposit;
        this.currency = currency;
        FileHandler.saveAccount(this);
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getFullName() { return firstName + " " + lastName; }
    public double getBalance() { return balance; }
    public String getCurrency() { return currency; }

    public void deposit(double amount, String currency) {
        if (this.currency.equals(currency)) {
            this.balance += amount;
            FileHandler.updateAccount(this);
        } else {
            System.out.println("Currency mismatch. Deposit failed.");
        }
    }

    public void withdraw(double amount, String currency) {
        if (this.currency.equals(currency) && this.balance >= amount) {
            this.balance -= amount;
            FileHandler.updateAccount(this);
        } else {
            System.out.println("Currency mismatch or insufficient funds. Withdrawal failed.");
        }
    }
    // public String getFormattedBalance() {
    //     return String.format("%.2f", this.balance);
    // }
}
