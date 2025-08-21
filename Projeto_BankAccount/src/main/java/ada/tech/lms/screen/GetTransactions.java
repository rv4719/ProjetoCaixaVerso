package ada.tech.lms.screen;

import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.Transaction;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class GetTransactions implements ExecutedOption{

    private BankService bankService;
    private Scanner scanner;
    private User userAccount;

    public GetTransactions(BankService bankService, User userAccount) {
        this.bankService = bankService;
        this.userAccount = userAccount;
    }

    @Override
    public void execute() throws IOException {
        BankAccount account = bankService.findAccountByUser(userAccount);
//        Path dir = Path.of(".\\src\\transactions_statements");
//        Files.createDirectories(dir);
//        Path path = dir.resolve(account.getOwner().getCpf()+".txt");
        System.out.println("Cliente: "+account.getOwner().getName());
        System.out.println("Número da Conta: "+account.getAccountNumber());
        account.listaTransaction.sort(Comparator.comparing(Transaction::getData).reversed());
        account.listaTransaction.forEach(System.out::println);

//        try (Stream<String> transactions = Files.lines(path, StandardCharsets.UTF_8)){
//            transactions.forEach(System.out::println);
//        };

//        for (Transaction transaction:account.listaTransaction){
//            System.out.println("Data: "+transaction.getData());
//            System.out.println("Tipo de transação: "+transaction.getType());
//            System.out.println("Valor: "+transaction.getAmount());
//            System.out.println("Saldo após a operação: "+account.getBalance());
//        }
    }
}
