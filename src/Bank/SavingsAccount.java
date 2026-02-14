package Bank;

public class SavingsAccount extends Account {

    public SavingsAccount(String name, int accNo, double balance, String password) {
        super(name, accNo, balance, AccountType.SAVINGS, password);
    }

    @Override
    public void withdraw(double amount) {

        if (amount <= 0)
            throw new IllegalArgumentException("Invalid withdrawal amount");

        if (amount > balance)
            throw new IllegalArgumentException("Insufficient balance");

        balance -= amount;
        addTransaction("WITHDRAW", amount);
    }
}
