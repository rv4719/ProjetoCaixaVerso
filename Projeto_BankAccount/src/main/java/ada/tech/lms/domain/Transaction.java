package ada.tech.lms.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDateTime data;
    private String type;
    private double amount;
    private double actualBalance;

    public Transaction(LocalDateTime data, String type, double amount,double actualBalance) {
        this.data = data;
        this.type = type;
        this.amount = amount;
        this.actualBalance = actualBalance;
    }

    public void setActualBalance(){

    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return String.format("Data/hora: %s - Valor: R$ %.2f - Transação: %s - Saldo: R$ %.2f",
                data.format(formatter),amount,type,actualBalance);

    }
}
