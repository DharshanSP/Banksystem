package Bank;

import java.time.LocalDateTime;

public class Transaction {

    private String type;
    private double amount;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public String toFileString() {
        return type + "," + amount + "," + dateTime;
    }

    public static Transaction fromFileString(String line) {
        String[] parts = line.split(",");
        return new Transaction(parts[0], Double.parseDouble(parts[1]));
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getDateTime() { return dateTime; }
}
