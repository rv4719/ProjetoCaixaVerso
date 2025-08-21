package ada.tech.lms.domain;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class BankAccount {
    protected String accountNumber;
    protected User owner;
    protected double balance;
    public ArrayList<Transaction> listaTransaction = new ArrayList();

    public BankAccount(String accountNumber, User owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public abstract void withdraw(double amount) throws IOException;

    public void deposit(double amount) throws IOException {
        this.balance += amount;

        Path dir = Path.of(".\\src\\transactions_statements");
        Files.createDirectories(dir);
        Path path = dir.resolve(this.getOwner().getCpf()+".txt");
//        Path path = dir.resolve("teste"+".txt");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = LocalDateTime.now().format(formatter);

//        Files.writeString(path,dataFormatada+"\n", StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
//        Files.writeString(path,"CREDIT\n", StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
//        Files.writeString(path, "R$ "+String.valueOf(amount)+"\n", StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
//        Files.writeString(path, "Saldo após a operação: R$"+String.valueOf(this.getBalance())+"\n", StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);

//        Files.writeString(path, String.format(
//                "Data/hora: %s - Valor: R$ %.2f - Transação: CREDIT - Saldo: R$ %.2f \n",
//                dataFormatada, amount, this.getBalance()), StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND));

        this.listaTransaction.add(new Transaction(LocalDateTime.now(),"CREDIT",amount,this.getBalance()));

        Files.writeString(path, String.format(
                "Data/hora: %s - Valor: R$ %.2f - Transação: CREDIT - Saldo: R$ %.2f - Número da conta: %s  - Cliente: %s \n",
                dataFormatada, amount, this.getBalance(),this.accountNumber,this.owner.getName()), StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);


    }
    public double getBalance() {
        return balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public User getOwner() {
        return owner;
    }

}