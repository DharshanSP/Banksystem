package Bank;

import java.io.*;
import java.util.*;

public class BankService {

    private List<Account> accounts = new ArrayList<>();

    public void createAccount(String name, int accNo,
                              double balance, String password) {

        accounts.add(new SavingsAccount(name, accNo, balance, password));
    }

    public Account findAccount(int accNo) {

        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo)
                return acc;
        }
        return null;
    }

    public Account login(int accNo, String password) {

        Account acc = findAccount(accNo);

        if (acc != null && acc.getPassword().equals(password))
            return acc;

        return null;
    }

    public void saveToFile() {

        try (FileWriter writer = new FileWriter("accounts.txt")) {

            for (Account acc : accounts) {

                writer.write("ACCOUNT|" +
                        acc.getName() + "|" +
                        acc.getAccountNumber() + "|" +
                        acc.getBalance() + "|" +
                        acc.getPassword() + "\n");

                for (Transaction t : acc.getTransactions()) {
                    writer.write("TRANSACTION|" +
                            acc.getAccountNumber() + "|" +
                            t.toFileString() + "\n");
                }
            }

        } catch (Exception e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    public void loadFromFile() {

        try {

            File file = new File("accounts.txt");
            if (!file.exists()) return;

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.startsWith("ACCOUNT|")) {

                    String[] data = line.split("\\|");

                    String name = data[1];
                    int accNo = Integer.parseInt(data[2]);
                    double balance = Double.parseDouble(data[3]);
                    String password = data[4];

                    createAccount(name, accNo, balance, password);

                } else if (line.startsWith("TRANSACTION|")) {

                    String[] data = line.split("\\|");

                    int accNo = Integer.parseInt(data[1]);
                    Transaction t =
                            Transaction.fromFileString(data[2]);

                    Account acc = findAccount(accNo);

                    if (acc != null)
                        acc.getTransactions().add(t);
                }
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }
}
