package ada.tech.lms.domain;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleAccount extends BankAccount {

    public SimpleAccount(String accountNumber, User owner, double balance) {
        super(accountNumber, owner, balance);
    }

    @Override
    public void withdraw(double amount) throws IOException {
        if (amount <= balance) {
            balance -= amount;
            Path dir = Path.of(".\\src\\transactions_statements");
            Files.createDirectories(dir);
            Path path = dir.resolve(this.getOwner().getCpf()+".txt");
//        Path path = dir.resolve("teste"+".txt");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = LocalDateTime.now().format(formatter);

            this.listaTransaction.add(new Transaction(LocalDateTime.now(),"DEBIT",-amount,this.getBalance()));

//            Files.writeString(path,dataFormatada+"\n", StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
//            Files.writeString(path,"DEBIT\n", StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
//            Files.writeString(path, "R$ "+String.valueOf(-amount)+"\n", StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
//            Files.writeString(path, "Saldo após a operação: R$"+String.valueOf(this.getBalance())+"\n", StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
//            Files.writeString(path, String.format(
//                    "Data/hora: %s - Valor: R$ %.2f - Transação: DEBIT - Saldo: R$ %.2f \n",
//                    dataFormatada, -amount, this.getBalance(), StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND));


            Files.writeString(path, String.format(
                    "Data/hora: %s - Valor: R$ %.2f - Transação: DEBIT - Saldo: R$ %.2f - Número da conta: %s  - Cliente: %s \n",
                    dataFormatada, -amount, this.getBalance(),this.accountNumber,this.owner.getName()), StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);

//            this.listaTransaction.add(new Transaction(LocalDateTime.now(),"DEBIT",-amount,this.getBalance()));
        } else {
            throw new IllegalArgumentException("Insufficient balance.");
        }
    }
}