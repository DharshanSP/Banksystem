package Bank;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    private BankService bank;

    public LoginScreen() {

        bank = new BankService();
        bank.loadFromFile();

        setTitle("Smart Bank");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 15, 15));

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");
        JButton exitBtn = new JButton("Exit");

        add(new JLabel("SMART BANK SYSTEM", JLabel.CENTER));
        add(loginBtn);
        add(registerBtn);
        add(exitBtn);

        loginBtn.addActionListener(e -> login());
        registerBtn.addActionListener(e -> register());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void login() {

        JTextField accField = new JTextField();
        JPasswordField passField = new JPasswordField();

        Object[] message = {
                "Account Number:", accField,
                "Password:", passField
        };

        int option = JOptionPane.showConfirmDialog(
                this, message, "Login",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {

            int accNo = Integer.parseInt(accField.getText());
            String password =
                    new String(passField.getPassword());

            Account acc = bank.login(accNo, password);

            if (acc != null) {
                dispose();
                new BankGUI(bank, acc).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                        this, "Invalid Credentials");
            }
        }
    }

    private void register() {

        JTextField nameField = new JTextField();
        JTextField accField = new JTextField();
        JTextField balField = new JTextField();
        JPasswordField passField = new JPasswordField();

        Object[] message = {
                "Name:", nameField,
                "Account Number:", accField,
                "Initial Balance:", balField,
                "Password:", passField
        };

        int option = JOptionPane.showConfirmDialog(
                this, message, "Register",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {

            bank.createAccount(
                    nameField.getText(),
                    Integer.parseInt(accField.getText()),
                    Double.parseDouble(balField.getText()),
                    new String(passField.getPassword())
            );

            bank.saveToFile();

            JOptionPane.showMessageDialog(
                    this, "Account Created!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new LoginScreen().setVisible(true));
    }
}
