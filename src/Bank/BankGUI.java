package Bank;

import javax.swing.*;
import java.awt.*;

public class BankGUI extends JFrame {

    private BankService bank;
    private Account loggedAccount;

    public BankGUI(BankService bank, Account acc) {

        this.bank = bank;
        this.loggedAccount = acc;

        setTitle("Welcome " + acc.getName());
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton balanceBtn = new JButton("See Balance");
        JButton transactionsBtn = new JButton("Transactions");
        JButton logoutBtn = new JButton("Logout");

        add(depositBtn);
        add(withdrawBtn);
        add(balanceBtn);
        add(transactionsBtn);
        add(logoutBtn);

        depositBtn.addActionListener(e -> deposit());
        withdrawBtn.addActionListener(e -> withdraw());
        balanceBtn.addActionListener(e -> showBalance());
        transactionsBtn.addActionListener(e -> showTransactions());
        logoutBtn.addActionListener(e -> logout());
    }

    private void deposit() {

        try {
            double amount = Double.parseDouble(
                    JOptionPane.showInputDialog(this,
                            "Enter Deposit Amount"));

            loggedAccount.deposit(amount);
            bank.saveToFile();

            JOptionPane.showMessageDialog(this,
                    "Deposit Successful!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void withdraw() {

        try {
            double amount = Double.parseDouble(
                    JOptionPane.showInputDialog(this,
                            "Enter Withdrawal Amount"));

            loggedAccount.withdraw(amount);
            bank.saveToFile();

            JOptionPane.showMessageDialog(this,
                    "Withdrawal Successful!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showBalance() {

        JOptionPane.showMessageDialog(
                this,
                "Current Balance: ₹" + loggedAccount.getBalance(),
                "Account Balance",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showTransactions() {

        StringBuilder sb = new StringBuilder();

        for (Transaction t : loggedAccount.getTransactions()) {
            sb.append(t.toFileString()).append("\n");
        }

        JOptionPane.showMessageDialog(
                this,
                sb.length() == 0 ? "No Transactions" : sb.toString(),
                "Transaction History",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void logout() {

        bank.saveToFile();
        dispose();
        new LoginScreen().setVisible(true);
    }
}
