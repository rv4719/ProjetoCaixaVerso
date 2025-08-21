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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = LocalDateTime.now().format(formatter);

            this.listaTransaction.add(new Transaction(LocalDateTime.now(),"DEBIT",-amount,this.getBalance()));


            Files.writeString(path, String.format(
                    "Data/hora: %s - Valor: R$ %.2f - Transação: DEBIT - Saldo: R$ %.2f - Número da conta: %s  - Cliente: %s \n",
                    dataFormatada, -amount, this.getBalance(),this.accountNumber,this.owner.getName()), StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);

        } else {
            throw new IllegalArgumentException("Insufficient balance.");
        }
    }
}
