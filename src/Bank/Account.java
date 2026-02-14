package Bank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private String name;
    private int accountNumber;
    private String password;
    protected double balance;
    private LocalDate createdDate;
    private AccountType type;

    protected List<Transaction> transactions = new ArrayList<>();

    public Account(String name, int accountNumber, double balance,
                   AccountType type, String password) {

        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.password = password;
        this.createdDate = LocalDate.now();
    }

    public String getName() { return name; }
    public int getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getPassword() { return password; }
    public List<Transaction> getTransactions() { return transactions; }

    protected void addTransaction(String type, double amount) {
        transactions.add(new Transaction(type, amount));
    }

    public void deposit(double amount) {

        if (amount <= 0)
            throw new IllegalArgumentException("Invalid deposit amount");

        balance += amount;
        addTransaction("DEPOSIT", amount);
    }

    public abstract void withdraw(double amount);
}
